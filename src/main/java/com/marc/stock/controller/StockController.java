package com.marc.stock.controller;

import com.marc.stock.ws.StockWS;
import org.springframework.http.ResponseEntity;

public interface StockController {
    ResponseEntity<?> getStock(String symbol);
    ResponseEntity<?> getStocks();
    ResponseEntity<?> createStock(StockWS stockWS);
    ResponseEntity<?> updateStock(String symbol, StockWS stockWS);
}
