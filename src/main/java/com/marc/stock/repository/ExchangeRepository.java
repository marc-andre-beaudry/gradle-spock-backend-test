package com.marc.stock.repository;

import com.marc.stock.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Exchange findBySymbol(String symbol);
}
