package com.marc.stock.service

import com.marc.stock.domain.StockBean
import com.marc.stock.entity.Exchange
import com.marc.stock.entity.Stock
import com.marc.stock.repository.ExchangeRepository
import com.marc.stock.repository.StockRepository
import com.marc.stock.service.exception.ExchangeNotFoundException
import spock.lang.Specification

class StockServiceSpec extends Specification {

    StockRepository stockRepository
    ExchangeRepository exchangeRepository

    StockService stockService

    def setup() {
        stockRepository = Mock()
        exchangeRepository = Mock()
        stockService = new StockServiceImpl(stockRepository: stockRepository, exchangeRepository: exchangeRepository)
    }

    def "empty repository"() {
        when:
        def stocks = stockService.getStocks()
        then:
        stockRepository.findAll() >> Collections.emptyList()
        stocks.size() == 0
    }

    def "repository with stocks"() {
        given: "a collection of stocks"
            def nasdaq = new Exchange(symbol: "NASDAQ")
            def apple = new Stock(symbol: "AAPL", exchange: nasdaq)
            def microsoft = new Stock(symbol: "MSFT", exchange: nasdaq)
            def tesla = new Stock(symbol: "TSLA", exchange: nasdaq)
            def stocks = Arrays.asList(apple, microsoft, tesla)
        when:
            def foundStocks = stockService.getStocks()
        then:
            stockRepository.findAll() >> stocks
            foundStocks.size() == 3
    }

    def "symbol not found then return empty optional"() {
        given: "an unknown symbol"
            def symbol = "AAPL"
        when:
            def optionalStockBean = stockService.getStock(symbol)
        then:
            stockRepository.findBySymbol(symbol) >> null
            optionalStockBean != null
            optionalStockBean.isPresent() == false
    }

    def "create stock without valid exchange throws an exception"() {
        given: "a new stock"
            def stockSymbol = "AAPL"
            def exchangeSymbol = "NASDAQ"
            def stockBean = new StockBean(symbol: stockSymbol, exchangeSymbol: exchangeSymbol)

        when: "we create the stock"
            stockService.createStock(stockBean)

        then: "we try to find the exchange by its symbol"
            exchangeRepository.findBySymbol(exchangeSymbol) >> null

        and: "an exception is thrown"
            thrown(ExchangeNotFoundException)
    }

    def "create a stock with a valid exchange returns the created stock"() {
        given: "a new stock"
            def stockSymbol = "AAPL"
            def exchangeSymbol = "NASDAQ"
            def exchange = new Exchange(symbol: exchangeSymbol)
            def stock = new Stock(id: 1, symbol: stockSymbol, exchange: exchange)
            def stockBean = new StockBean(symbol: stockSymbol, exchangeSymbol: exchangeSymbol)

        when: "we create the stock"
            def createdStockBean = stockService.createStock(stockBean)

        then: "we try to find the exchange by its symbol"
            exchangeRepository.findBySymbol(exchangeSymbol) >> exchange
        and: "persisting the new stock"
            stockRepository.save(_) >> stock

        and: "a bean is returned"
            createdStockBean != null
            createdStockBean.getSymbol() == stockSymbol
            createdStockBean.getExchangeSymbol() == exchangeSymbol
    }
}