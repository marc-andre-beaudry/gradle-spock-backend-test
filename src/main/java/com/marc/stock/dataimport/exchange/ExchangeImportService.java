package com.marc.stock.dataimport.exchange;

import com.marc.stock.domain.ExchangeBean;
import com.marc.stock.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class ExchangeImportService {

    @Autowired
    private ExchangeService exchangeService;

    public void loadExchanges() {
        try (BufferedReader br = getExchangesStream()) {
            br.lines().skip(1).forEach(line -> {
                ExchangeBean exchangeBean = new ExchangeBean();
                exchangeBean.setSymbol(line);
                exchangeService.createExchange(exchangeBean);
            });
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private BufferedReader getExchangesStream() {
        return new BufferedReader(new InputStreamReader(ExchangeImportService.class.getResourceAsStream("/exchanges.data")));
    }
}
