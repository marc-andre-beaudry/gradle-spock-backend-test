package com.marc.stock.domain;

import com.marc.stock.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ExecutionBean {

    private String uuid;
    private int quantity;
    private String symbol;
    private OrderSide side;
    private BigDecimal price;
    private Date timestamp;
    private BigDecimal commission;
    private String orderUuid;
    private String accountUuid;
    private Date lastUpdated;

    public static ExecutionBean fromExecution(Execution execution) {
        ExecutionBean executionBean = new ExecutionBean();
        executionBean.setUuid(execution.getUuid());
        executionBean.setQuantity(execution.getQuantity());
        executionBean.setSymbol(execution.getStock().getSymbol());
        executionBean.setSide(execution.getSide());
        executionBean.setPrice(execution.getPrice());
        executionBean.setTimestamp(execution.getTimestamp());
        executionBean.setCommission(execution.getCommission());
        executionBean.setOrderUuid(execution.getOrder().getUuid());
        executionBean.setAccountUuid(execution.getAccount().getUuid());
        executionBean.setLastUpdated(execution.getLastUpdated());
        return executionBean;
    }
}
