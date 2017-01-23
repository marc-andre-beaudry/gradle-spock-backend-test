package com.marc.stock.service;

import com.marc.stock.domain.ExchangeBean;

import java.util.List;
import java.util.Optional;

public interface ExchangeService {
    Optional<ExchangeBean> getExchange(String symbol);
    List<ExchangeBean> getExchanges();
    ExchangeBean createExchange(ExchangeBean exchangeBean);
}
