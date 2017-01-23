package com.marc.stock.dataimport;

import com.marc.stock.domain.StockBean;
import com.marc.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CompanyImportService {

    @Autowired
    private StockService stockService;

    public void loadCompagnies() {
        try (BufferedReader br = getCompanyDataStream()) {
            br.lines().skip(1).forEach(line -> {
                String[] splittedLine = line.split("\\|");
                StockBean stockBean = new StockBean();
                stockBean.setSymbol(splittedLine[0]);
                stockBean.setName(splittedLine[1]);
                stockService.createStock(stockBean);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private BufferedReader getCompanyDataStream() {
        return new BufferedReader(new InputStreamReader(CompanyImportService.class.getResourceAsStream("/company_seed.data")));
    }
}
