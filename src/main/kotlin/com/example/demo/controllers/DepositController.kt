package com.example.demo.controllers;

import com.example.demo.entities.*
import com.example.demo.services.DepositService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import java.lang.IllegalArgumentException


@RestController
@RequestMapping("/deposits")
class DepositController {
    @Autowired
    lateinit var depositService: DepositService

    @GetMapping
    fun index(): List<Deposit> {
        return depositService.findAll()
    }

    @PostMapping
    fun create(@RequestBody depositDTO: DepositDTO): Deposit {
        try {
            return depositService.createDeposit(depositDTO.depositor, depositDTO.value)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }
}
