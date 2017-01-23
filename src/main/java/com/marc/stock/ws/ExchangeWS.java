package com.marc.stock.ws;

import com.marc.stock.domain.ExchangeBean;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ExchangeWS {

    @Size(min = 1, max = 10)
    private String symbol;

    @Size(max = 50)
    private String name;

    public static ExchangeWS fromBean(ExchangeBean exchangeBean) {
        ExchangeWS exchangeWS = new ExchangeWS();
        exchangeWS.setSymbol(exchangeBean.getSymbol());
        exchangeWS.setName(exchangeBean.getName());
        return exchangeWS;
    }
}
