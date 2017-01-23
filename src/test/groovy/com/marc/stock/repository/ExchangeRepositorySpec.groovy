package com.marc.stock.repository

import com.marc.stock.entity.Exchange
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
class ExchangeRepositorySpec extends Specification {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    ExchangeRepository exchangeRepository

    def "empty repository"() {
        expect:
        exchangeRepository.findAll().size() == 0
    }

    def "find by symbol"() {
        given:
        def exchange = new Exchange(symbol:"NASDAQ")
        this.entityManager.persist(exchange)

        when:
        def foundExchange = this.exchangeRepository.findBySymbol("NASDAQ")

        then:
        foundExchange != null
        foundExchange.getId() > 0
        foundExchange.getSymbol() == "NASDAQ"
    }
}