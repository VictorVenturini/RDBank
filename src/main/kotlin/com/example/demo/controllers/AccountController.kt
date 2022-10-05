package com.example.demo.controllers

import com.example.demo.entities.Account
import com.example.demo.entities.User
import com.example.demo.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.webjars.NotFoundException
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/accounts")
class AccountController {
    @Autowired
    lateinit var accountService: AccountService

    @GetMapping
    fun index(): List<Account> {
        return accountService.findAll()
    }

    @GetMapping("/{cpf}")
    fun findByCpf(@PathVariable cpf: String) {
        try {
            accountService.findByCpf(cpf)
        } catch (e: NotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody user: User): Account {
        try {
            return accountService.createAccount(user)
        } catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }
}