package com.marc.stock.service;

import com.marc.stock.domain.ExchangeBean;
import com.marc.stock.entity.Exchange;
import com.marc.stock.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ExchangeBean> getExchange(String symbol) {
        Exchange exchange = exchangeRepository.findBySymbol(symbol);
        return Optional.ofNullable(exchange).map(x -> ExchangeBean.fromExchange(exchange));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeBean> getExchanges() {
        List<ExchangeBean> exchangeBeanList = new ArrayList<>();
        for(Exchange exchange : exchangeRepository.findAll()) {
            exchangeBeanList.add(ExchangeBean.fromExchange(exchange));
        }
        return exchangeBeanList;
    }

    @Override
    @Transactional
    public ExchangeBean createExchange(ExchangeBean exchangeBean) {
        Exchange exchange = new Exchange();
        exchange.setSymbol(exchangeBean.getSymbol());
        exchange.setName(exchangeBean.getName());

        Exchange createdExchange = exchangeRepository.save(exchange);
        return ExchangeBean.fromExchange(createdExchange);
    }
}
