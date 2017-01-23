package com.marc.stock.repository

import com.marc.stock.entity.Stock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@DataJpaTest
class StockRepositorySpec extends Specification {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    StockRepository stockRepository

    def "empty repository"() {
        expect:
        stockRepository.findAll().size() == 0
    }

    def "find by symbol"() {
        given:
        def stock = new Stock(symbol:"AAPL")
        this.entityManager.persist(stock)

        when:
        def foundStock = this.stockRepository.findBySymbol("AAPL")

        then:
        foundStock != null
        foundStock.getId() > 0
        foundStock.getSymbol() == "AAPL"
    }
}