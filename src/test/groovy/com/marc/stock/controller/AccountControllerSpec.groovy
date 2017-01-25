package com.marc.stock.controller

import com.marc.stock.service.AccountService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AccountControllerSpec extends Specification {

    AccountService accountService = Mock(AccountService)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new AccountControllerImpl(accountService: accountService)).build()

    def "get accounts with empty repository"() {
        given: "an empty account collection"
        def accountCollection = Collections.emptyList()

        when: "we request all accounts"
        def response = mockMvc.perform(get("/api/accounts").contentType(APPLICATION_JSON))

        then: "the account service is invoked and return the account collection"
        1 * accountService.getAccounts() >> accountCollection

        and: "the http status is OK"
        response
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(content().json("[]"))
    }
}