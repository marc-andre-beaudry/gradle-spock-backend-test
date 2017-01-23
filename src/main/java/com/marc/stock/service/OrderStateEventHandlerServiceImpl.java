package com.marc.stock.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderStateEventHandlerServiceImpl implements OrderStateEventHandlerService {

    @Override
    public void receive(String orderStateEvent) {
        log.info(orderStateEvent);
    }
}
