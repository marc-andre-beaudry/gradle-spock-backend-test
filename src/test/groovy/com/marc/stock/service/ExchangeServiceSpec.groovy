package com.marc.stock.service

import com.marc.stock.entity.Exchange
import com.marc.stock.repository.ExchangeRepository
import spock.lang.Specification


class ExchangeServiceSpec extends Specification {

    ExchangeRepository exchangeRepository

    ExchangeService exchangeService

    def setup() {
        exchangeRepository = Mock()
        exchangeService = new ExchangeServiceImpl(exchangeRepository: exchangeRepository)
    }

    def "empty repository"() {
        given: "an empty repository"
        def exchanges = Collections.emptyList()

        when:
        def foundExchanges = exchangeService.getExchanges()
        then:
        exchangeRepository.findAll() >> exchanges
        foundExchanges.size() == 0
    }

    def "repository with exchanges"() {
        given: "2 exchanges"
        def nasdaq = new Exchange(symbol: "NASDAQ")
        def nyse = new Exchange(symbol: "NYSE")
        def exchanges = Arrays.asList(nasdaq, nyse)

        when:
        def foundExchanges = exchangeService.getExchanges()

        then:
        exchangeRepository.findAll() >> exchanges
        foundExchanges.size() == exchanges.size()
    }
}