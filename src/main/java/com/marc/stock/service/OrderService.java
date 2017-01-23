package com.marc.stock.service;

import com.marc.stock.domain.OrderBean;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<OrderBean> getOrder(String uuid);
    List<OrderBean> getOrders();
    boolean isModifiable(OrderBean orderBean);
    OrderBean createOrder(OrderBean orderBean);
    OrderBean cancelOrder(OrderBean orderBean);
    OrderBean changeType(OrderBean orderBean);
}
