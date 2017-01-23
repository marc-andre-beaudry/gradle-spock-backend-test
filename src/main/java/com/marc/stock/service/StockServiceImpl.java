package com.marc.stock.service;

import com.marc.stock.domain.StockBean;
import com.marc.stock.entity.Exchange;
import com.marc.stock.entity.Stock;
import com.marc.stock.repository.ExchangeRepository;
import com.marc.stock.repository.StockRepository;
import com.marc.stock.service.exception.ExchangeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<StockBean> getStock(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol);
        return Optional.ofNullable(stock).map(x -> StockBean.fromStock(stock));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockBean> getStocks() {
        List<StockBean> stockBeanList = new ArrayList<>();
        for(Stock stock : stockRepository.findAll()) {
            stockBeanList.add(StockBean.fromStock(stock));
        }
        return stockBeanList;
    }

    @Override
    @Transactional
    public StockBean createStock(StockBean stockBean) {
        Exchange exchange = exchangeRepository.findBySymbol(stockBean.getExchangeSymbol());
        if (exchange == null) {
            throw new ExchangeNotFoundException();
        }
        Stock stock = new Stock();
        stock.setSymbol(stockBean.getSymbol());
        stock.setExchange(exchange);
        stock.setSector(stockBean.getSector());
        stock.setIndustry(stockBean.getIndustry());
        stock.setIpoYear(stockBean.getIpoYear());

        Stock createdStock = stockRepository.save(stock);
        return StockBean.fromStock(createdStock);
    }
}
