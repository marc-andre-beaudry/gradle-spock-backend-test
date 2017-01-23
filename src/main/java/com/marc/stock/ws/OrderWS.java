package com.marc.stock.ws;

import com.marc.stock.domain.OrderBean;
import com.marc.stock.entity.OrderSide;
import com.marc.stock.entity.OrderState;
import com.marc.stock.entity.OrderType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderWS {

    private String uuid;
    @NotNull
    private String symbol;
    private int openQuantity;
    private int filledQuantity;
    private int canceledQuantity;
    @NotNull
    private OrderSide side;
    @NotNull
    private OrderType orderType;
    private BigDecimal avgExecPrice;
    private BigDecimal lastExecPrice;
    private OrderState state;

    private Date creationTime;
    private Date updateTime;

    private BigDecimal commissionCharged;
    @NotNull
    private String accountUuid;

    public static OrderWS fromBean(OrderBean orderBean) {
        OrderWS orderWS = new OrderWS();
        return orderWS;
    }
}
