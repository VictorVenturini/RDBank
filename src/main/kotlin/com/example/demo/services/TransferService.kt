package com.example.demo.services

import com.example.demo.entities.Transfer
import com.example.demo.entities.User
import com.example.demo.repositories.TransferRepository
import com.example.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class TransferService {
    @Autowired
    lateinit var transferRepository: TransferRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun findAll(): List<Transfer> {
        return transferRepository.findAll()
    }

    fun createTransfer(senderCpf: String, receiveCpf: String, value: Long): Transfer {
        if(userRepository.existsByCpf(senderCpf) && userRepository.existsByCpf(receiveCpf)) {
            val sender: User = userRepository.findByCpf(senderCpf)
            val receiver: User = userRepository.findByCpf(receiveCpf)

            if(sender.account?.balance!! >= value) {
                sender.account?.balance = sender.account?.balance!! - value
                receiver.account?.balance = receiver.account?.balance!! + value

                val transfer = Transfer(
                    sender = sender,
                    receiver = receiver,
                    value = value,
                    date = Timestamp.valueOf(LocalDateTime.now())
                )
                return transferRepository.save(transfer)
            } else {
                throw Exception("O saldo do transmitente é insuficiente")
            }
            } else {
                throw NotFoundException("O cpf inserido não foi encontrado")
        }
    }
}