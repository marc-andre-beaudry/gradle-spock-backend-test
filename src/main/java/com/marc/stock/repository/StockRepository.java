package com.marc.stock.repository;

import com.marc.stock.entity.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
    Stock findBySymbol(String symbol);
}
