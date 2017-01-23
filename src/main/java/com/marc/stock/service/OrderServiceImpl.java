package com.marc.stock.service;

import com.marc.stock.domain.OrderBean;
import com.marc.stock.entity.Order;
import com.marc.stock.entity.OrderState;
import com.marc.stock.repository.AccountRepository;
import com.marc.stock.repository.OrderRepository;
import com.marc.stock.repository.StockRepository;
import com.marc.stock.service.exception.InvalidOrderStateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UniqueIdService uniqueIdService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ClockServiceImpl clockService;

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderBean> getOrder(String uuid) {
        Order order = orderRepository.findByUuid(uuid);
        return Optional.ofNullable(order).map(x -> OrderBean.fromOrder(order));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderBean> getOrders() {
        List<OrderBean> positionBeanList = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            positionBeanList.add(OrderBean.fromOrder(order));
        }
        return positionBeanList;
    }

    @Override
    public boolean isModifiable(OrderBean orderBean) {
        return isStateCancellable(orderBean.getState());
    }

    private boolean isStateCancellable(OrderState orderState) {
        return OrderState.Accepted.equals(orderState) || OrderState.Partial.equals(orderState);
    }

    @Override
    @Transactional
    public OrderBean createOrder(OrderBean orderBean) {
        Order order = new Order();
        order.setUuid(uniqueIdService.getUuid());
        order.setOpenQuantity(orderBean.getOpenQuantity());
        order.setSide(orderBean.getSide());
        order.setOrderType(orderBean.getOrderType());
        order.setState(OrderState.Accepted);
        order.setStock(stockRepository.findBySymbol(orderBean.getSymbol()));
        order.setAccount(accountRepository.findByUuid(orderBean.getAccountUuid()));
        order.setCreationTime(clockService.now());
        Order createdOrder = orderRepository.save(order);
        stringRedisTemplate.convertAndSend("orderStateEvent", "order created");
        return OrderBean.fromOrder(createdOrder);
    }

    @Override
    @Transactional
    public OrderBean cancelOrder(OrderBean orderBean) {
        Order order = orderRepository.findByUuid(orderBean.getUuid());
        if (isStateCancellable(order.getState())) {
            order.setState(OrderState.CancelPending);
        } else {
            throw new InvalidOrderStateException();
        }
        return OrderBean.fromOrder(order);
    }

    @Override
    @Transactional
    public OrderBean changeType(OrderBean orderBean) {
        throw new NotImplementedException();
    }
}
