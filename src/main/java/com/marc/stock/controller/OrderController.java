package com.marc.stock.controller;

import com.marc.stock.ws.OrderWS;
import org.springframework.http.ResponseEntity;

public interface OrderController {
    ResponseEntity<?> getOrder(String orderUuid);
    ResponseEntity<?> getOrders();
    ResponseEntity<?> createOrder(OrderWS orderWS);
    ResponseEntity<?> editOrder(String orderUuid, OrderWS orderWS);
}
