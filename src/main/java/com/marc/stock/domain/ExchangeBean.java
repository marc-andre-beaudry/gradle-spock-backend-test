package com.marc.stock.domain;

import com.marc.stock.entity.Exchange;
import com.marc.stock.ws.ExchangeWS;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeBean {
    private Long id;
    private String symbol;
    private String name;

    public static ExchangeBean fromExchange(Exchange exchange) {
        ExchangeBean exchangeBean = new ExchangeBean();
        exchangeBean.setSymbol(exchange.getSymbol());
        exchangeBean.setId(exchange.getId());
        exchangeBean.setName(exchange.getName());
        return exchangeBean;
    }

    public static ExchangeBean fromExchangeWS(ExchangeWS exchangeWS) {
        ExchangeBean exchangeBean = new ExchangeBean();
        exchangeBean.setSymbol(exchangeWS.getSymbol());
        exchangeBean.setName(exchangeWS.getName());
        return exchangeBean;
    }
}
