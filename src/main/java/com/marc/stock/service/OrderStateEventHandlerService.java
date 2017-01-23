package com.marc.stock.service;

public interface OrderStateEventHandlerService {
    void receive(String orderStateEvent);
}
