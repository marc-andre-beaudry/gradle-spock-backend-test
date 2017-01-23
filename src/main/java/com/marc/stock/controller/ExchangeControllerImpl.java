package com.marc.stock.controller;

import com.marc.stock.domain.ExchangeBean;
import com.marc.stock.service.ExchangeService;
import com.marc.stock.ws.ExchangeWS;
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
public class ExchangeControllerImpl implements ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Override
    @GetMapping("/exchanges/{symbol}")
    public ResponseEntity<?> getExchange(@PathVariable String symbol) {
        Optional<ExchangeBean> exchangeBeanOptional = exchangeService.getExchange(symbol);
        if (!exchangeBeanOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ExchangeWS.fromBean(exchangeBeanOptional.get()));
    }

    @Override
    @GetMapping("/exchanges")
    public ResponseEntity<?> getExchanges() {
        List<ExchangeBean> exchangeBeans = exchangeService.getExchanges();
        List<ExchangeWS> exchanges = exchangeBeans.stream().map(exchangeBean -> ExchangeWS.fromBean(exchangeBean)).collect(Collectors.toList());
        return ResponseEntity.ok(exchanges);
    }

    @Override
    @PostMapping("/exchanges")
    public ResponseEntity<?> createExchange(@Valid @RequestBody ExchangeWS exchangeWS) {
        Optional<ExchangeBean> exchangeBeanOptional = exchangeService.getExchange(exchangeWS.getSymbol());
        if (exchangeBeanOptional.isPresent()) {
            return ResponseEntity.badRequest().body("exchange already exists");
        }

        ExchangeBean exchangeBean = ExchangeBean.fromExchangeWS(exchangeWS);
        exchangeService.createExchange(exchangeBean);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/exchanges/{symbol}")
    public ResponseEntity<?> updateExchange(@PathVariable String symbol, @Valid @RequestBody  ExchangeWS exchangeWS) {
        return null;
    }
}
