package com.marc.stock.domain;

import com.marc.stock.entity.Stock;
import com.marc.stock.ws.StockWS;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockBean {

    private String symbol;
    private String name;
    private String exchangeSymbol;
    private String sector;
    private String industry;
    private Date ipoYear;

    public static StockBean fromStock(Stock stock) {
        StockBean stockBean = new StockBean();
        stockBean.setSymbol(stock.getSymbol());
        stockBean.setExchangeSymbol(stock.getExchange().getSymbol());
        stockBean.setIndustry(stock.getIndustry());
        stockBean.setSector(stock.getSector());
        stockBean.setIpoYear(stock.getIpoYear());
        return stockBean;
    }

    public static StockBean fromStockWS(StockWS stockWS) {
        StockBean stockBean = new StockBean();
        stockBean.setSymbol(stockWS.getSymbol());
        stockBean.setExchangeSymbol(stockWS.getExchangeSymbol());
        stockBean.setIndustry(stockWS.getIndustry());
        stockBean.setSector(stockWS.getSector());
        stockBean.setIpoYear(stockWS.getIpoYear());
        return stockBean;
    }
}
