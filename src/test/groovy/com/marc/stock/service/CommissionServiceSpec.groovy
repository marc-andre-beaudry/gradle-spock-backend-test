package com.marc.stock.service

import spock.lang.Specification

class CommissionServiceSpec extends Specification {

    CommissionService commissionService

    def setup() {
        commissionService = new CommissionServiceImpl()
    }

    def "test clamping"() {
        expect:
        commissionService.estimate(size, price) == commission

        where:
        size    | price     | commission
        0       | 10G       | 0G // no trade, no commission

        1       | 1G        | 0.35G // flat price per share
        10000   | 1G        | 35G // flat price per share
        10000   | 0.1G      | 5G // max order price
        1000    | 0.1G      | 0.5G // max order price

        -1      | 1G        | 0.35G // negative number (short sale)
        -10000  | 1G        | 35G // negative number (short sale)
    }
}