package com.example.demo.controllers

import com.example.demo.entities.Transfer
import com.example.demo.entities.TransferDTO
import com.example.demo.services.TransferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.webjars.NotFoundException
import java.lang.Exception

@RestController
@RequestMapping("/transfers")
class TransferController {
    @Autowired
    lateinit var transferService: TransferService

    @GetMapping
    fun index(): List<Transfer> {
        return transferService.findAll()
    }

    @PostMapping
    fun create(@RequestBody transferDTO: TransferDTO): Transfer {
        try {
            return transferService.createTransfer(transferDTO.sender, transferDTO.receiver, transferDTO.value)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        } catch (e: NotFoundException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.localizedMessage)
        }
    }
}