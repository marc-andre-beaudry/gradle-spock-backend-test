package com.marc.stock.controller

import com.marc.stock.domain.OrderBean
import com.marc.stock.service.OrderService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OrderControllerSpec extends Specification {

    OrderService orderService = Mock(OrderService)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new OrderControllerImpl(orderService: orderService)).build()

    def "empty order collection"() {
        when:
        def response = mockMvc.perform(get("/api/orders").contentType(APPLICATION_JSON))

        then:
        1 * orderService.getOrders() >> Collections.emptyList()

        and:
        response.andExpect(status().isOk())
    }

    def "unknown order uuid"() {
        given:
        def uuid = "abcd"

        when:
        def response = mockMvc.perform(get("/api/orders/" + uuid).contentType(APPLICATION_JSON))

        then:
        1 * orderService.getOrder(uuid) >> Optional.empty()

        and:
        response.andExpect(status().isNotFound())
    }

    def "order uuid that exist"() {
        given:
        def uuid = "abcd"

        when:
        def response = mockMvc.perform(get("/api/orders/" + uuid).contentType(APPLICATION_JSON))

        then:
        1 * orderService.getOrder(uuid) >> Optional.of(new OrderBean())

        and:
        response.andExpect(status().isOk())
    }
}