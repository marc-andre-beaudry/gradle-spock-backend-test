package com.marc.stock.service;

import com.marc.stock.domain.StockBean;

import java.util.List;
import java.util.Optional;

public interface StockService {
    Optional<StockBean> getStock(String symbol);
    List<StockBean> getStocks();
    StockBean createStock(StockBean stockBean);
}
