package com.marc.stock.controller;

import com.marc.stock.domain.StockBean;
import com.marc.stock.service.StockService;
import com.marc.stock.ws.StockWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StockControllerImpl implements StockController {

    @Autowired
    private StockService stockService;

    @Override
    @GetMapping("/stocks/{symbol}")
    public ResponseEntity<?> getStock(@PathVariable String symbol) {
        Optional<StockBean> stockBeanOptional = stockService.getStock(symbol);
        if (!stockBeanOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(StockWS.fromBean(stockBeanOptional.get()));
    }

    @Override
    @GetMapping("/stocks")
    public ResponseEntity<?> getStocks() {
        List<StockBean> stockBeans = stockService.getStocks();
        List<StockWS> stocks = stockBeans.stream().map(stockBean -> StockWS.fromBean(stockBean)).collect(Collectors.toList());
        return ResponseEntity.ok(stocks);
    }

    @Override
    @PostMapping("/stocks")
    public ResponseEntity<?> createStock(@Valid @RequestBody StockWS stockWS) {
        Optional<StockBean> stockBeanOptional = stockService.getStock(stockWS.getSymbol());
        if (stockBeanOptional.isPresent()) {
            return ResponseEntity.badRequest().body("stock already exists");
        }

        StockBean stockBean = StockBean.fromStockWS(stockWS);
        stockService.createStock(stockBean);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/stocks/{symbol}")
    public ResponseEntity<?> updateStock(@PathVariable String symbol, StockWS stockWS) {
        return null;
    }
}
