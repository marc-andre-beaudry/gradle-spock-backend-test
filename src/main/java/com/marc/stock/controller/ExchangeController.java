package com.marc.stock.controller;

import com.marc.stock.ws.ExchangeWS;
import org.springframework.http.ResponseEntity;

public interface ExchangeController {
    ResponseEntity<?> getExchange(String symbol);
    ResponseEntity<?> getExchanges();
    ResponseEntity<?> createExchange(ExchangeWS exchangeWS);
    ResponseEntity<?> updateExchange(String symbol, ExchangeWS exchangeWS);
}
