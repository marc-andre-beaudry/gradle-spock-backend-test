package com.marc.stock.service.event;

import com.marc.stock.entity.OrderState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStateEvent {
    private String orderUuid;
    private OrderState previousState;
    private OrderState currentState;
}
