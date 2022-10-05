package com.example.demo

import com.example.demo.entities.Account
import com.example.demo.entities.User
import com.example.demo.repositories.AccountRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Test
    fun `test find all`() {
        val user = User(cpf = "724.885.726/24", name = "Victor Venturini")
        val account = Account(user = user, balance = 0)
        user.account = account
        accountRepository.save(account)

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].user").isMap)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].balance").isNumber)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `test create account`() {
        val user = User(cpf = "724.885.726/24", name = "Victor Venturini", Account(balance = 0))
        val account = Account(user = user, balance = 0)
        user.account = account
        val json = ObjectMapper().writeValueAsString(user)
        accountRepository.deleteAll()
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.user").isMap)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.balance").value(account.balance))
            .andDo(MockMvcResultHandlers.print())

        Assertions.assertFalse(accountRepository.findAll().isEmpty())
    }
}