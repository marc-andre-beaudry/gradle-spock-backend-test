package com.marc.stock.domain;

import com.marc.stock.entity.Order;
import com.marc.stock.entity.OrderSide;
import com.marc.stock.entity.OrderState;
import com.marc.stock.entity.OrderType;
import com.marc.stock.ws.OrderWS;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderBean {

    private String uuid;
    private String symbol;
    private int openQuantity;
    private int filledQuantity;
    private int canceledQuantity;
    private OrderSide side;
    private OrderType orderType;
    private BigDecimal avgExecPrice;
    private BigDecimal lastExecPrice;
    private OrderState state;

    private Date creationTime;
    private Date updateTime;

    private BigDecimal commissionCharged;
    private String accountUuid;

    public static OrderBean fromOrder(Order order) {
        OrderBean orderBean = new OrderBean();
        orderBean.setUuid(order.getUuid());
        orderBean.setSymbol(order.getStock().getSymbol());
        orderBean.setOpenQuantity(order.getOpenQuantity());
        orderBean.setFilledQuantity(order.getFilledQuantity());
        orderBean.setCanceledQuantity(order.getCanceledQuantity());
        orderBean.setSide(order.getSide());
        orderBean.setOrderType(order.getOrderType());
        orderBean.setAvgExecPrice(order.getAvgExecPrice());
        orderBean.setLastExecPrice(order.getLastExecPrice());
        orderBean.setState(order.getState());
        orderBean.setCreationTime(order.getCreationTime());
        orderBean.setUpdateTime(order.getLastUpdated());
        orderBean.setCommissionCharged(order.getCommissionCharged());
        orderBean.setAccountUuid(order.getAccount().getUuid());
        return orderBean;
    }

    public static OrderBean fromOrderWS(OrderWS orderWS) {
        OrderBean orderBean = new OrderBean();
        orderBean.setUuid(orderWS.getUuid());
        orderBean.setSymbol(orderWS.getSymbol());
        orderBean.setOpenQuantity(orderWS.getOpenQuantity());
        orderBean.setFilledQuantity(orderWS.getFilledQuantity());
        orderBean.setCanceledQuantity(orderWS.getCanceledQuantity());
        orderBean.setSide(orderWS.getSide());
        orderBean.setOrderType(orderWS.getOrderType());
        orderBean.setAvgExecPrice(orderWS.getAvgExecPrice());
        orderBean.setLastExecPrice(orderWS.getLastExecPrice());
        orderBean.setState(orderWS.getState());
        orderBean.setCreationTime(orderWS.getCreationTime());
        orderBean.setUpdateTime(orderWS.getUpdateTime());
        orderBean.setCommissionCharged(orderWS.getCommissionCharged());
        orderBean.setAccountUuid(orderWS.getAccountUuid());
        return orderBean;
    }
}
