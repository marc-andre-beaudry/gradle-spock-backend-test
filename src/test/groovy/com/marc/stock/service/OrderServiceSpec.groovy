package com.marc.stock.service

import com.marc.stock.domain.OrderBean
import com.marc.stock.entity.OrderState
import spock.lang.Specification


class OrderServiceSpec extends Specification {

    OrderService orderService

    def setup() {
        orderService = new OrderServiceImpl()
    }

    def "order modifiable"() {
        expect:
        orderService.isModifiable(new OrderBean(state: orderState)) == isModifiable

        where:
        orderState | isModifiable
        OrderState.Canceled | false
        OrderState.Accepted | true
        OrderState.Partial | true
        OrderState.CancelPending | false
        OrderState.Executed | false
        OrderState.PartialCanceled | false
        OrderState.Rejected | false
    }
}