package com.marc.stock.ws;

import com.marc.stock.domain.StockBean;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class StockWS {

    @Size(min = 1, max = 10)
    private String symbol;
    private String name;
    @Size(min = 1, max = 10)
    private String exchangeSymbol;
    private String sector;
    private String industry;
    private Date ipoYear;

    public static StockWS fromBean(StockBean stockBean) {
        StockWS stockWS = new StockWS();
        stockWS.setSymbol(stockBean.getSymbol());
        stockWS.setName(stockBean.getName());
        stockWS.setExchangeSymbol(stockBean.getExchangeSymbol());
        stockWS.setSector(stockBean.getSector());
        stockWS.setIndustry(stockBean.getIndustry());
        stockWS.setIpoYear(stockBean.getIpoYear());
        return stockWS;
    }
}
