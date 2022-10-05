package com.example.demo.services

import com.example.demo.entities.Deposit
import com.example.demo.entities.User
import com.example.demo.repositories.DepositRepository
import com.example.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.LocalDateTime

@Service
class DepositService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var depositRepository: DepositRepository

    fun findAll(): List<Deposit> {
        return depositRepository.findAll()
    }

    fun createDeposit(depositor: String, value: Long): Deposit {
        if (value > 2000){
            val depositor: User = userRepository.findByCpf(depositor)

            depositor?.account?.balance = depositor?.account?.balance?.plus(value)

            val deposit = Deposit(
                depositor = depositor,
                value = value,
                date = java.sql.Timestamp.valueOf(LocalDateTime.now())
            )

            return depositRepository.save(deposit)
        } else {
            throw Exception("Depositos acima de 2000 reais não são permitidos")
        }
    }
}