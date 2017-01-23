package com.marc.stock.repository;

import com.marc.stock.entity.Exchange;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRepository extends CrudRepository<Exchange, Long> {
    Exchange findBySymbol(String symbol);
}
