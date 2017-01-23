package com.marc.stock.controller;

import com.marc.stock.domain.OrderBean;
import com.marc.stock.entity.OrderState;
import com.marc.stock.service.AccountService;
import com.marc.stock.service.OrderService;
import com.marc.stock.service.StockService;
import com.marc.stock.ws.OrderWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StockService stockService;

    @Override
    @GetMapping("/orders/{orderUuid}")
    public ResponseEntity<?> getOrder(@PathVariable String orderUuid) {
        Optional<OrderBean> orderBeanOptional = orderService.getOrder(orderUuid);
        if (!orderBeanOptional.isPresent()) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(OrderWS.fromBean(orderBeanOptional.get()));
    }

    @Override
    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        List<OrderBean> beans = orderService.getOrders();
        List<OrderWS> orderWSList = beans.stream().map(orderBean -> OrderWS.fromBean(orderBean)).collect(Collectors.toList());
        return ResponseEntity.ok(orderWSList);
    }

    @Override
    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderWS orderWS) {
        if (!StringUtils.isEmpty(orderWS.getUuid())) {
            return ResponseEntity.badRequest().body("providing a uuid is not allowed");
        }
        OrderBean orderBean = OrderBean.fromOrderWS(orderWS);
        orderService.createOrder(orderBean);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/orders/{orderUuid}")
    public ResponseEntity<?> editOrder(@PathVariable String orderUuid, @RequestBody OrderWS orderWS) {
        if (!orderWS.getUuid().equals(orderUuid)) {
            // both uuid must match
            return ResponseEntity.badRequest().body("");
        }

        Optional<OrderBean> orderBeanOptional = orderService.getOrder(orderWS.getUuid());
        if (!orderBeanOptional.isPresent()) {
            // Cannot modify an order that doesn't exist
            return ResponseEntity.badRequest().body("");
        }

        if (!orderService.isModifiable(orderBeanOptional.get())) {
            return ResponseEntity.badRequest().body("");
        }

        // cancel
        if (orderBeanOptional.get().getState() == OrderState.Canceled) {
            orderService.cancelOrder(orderBeanOptional.get());
        }
        // change type

        //
        return null;
    }
}
