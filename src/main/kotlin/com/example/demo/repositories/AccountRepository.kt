package com.example.demo.repositories

import com.example.demo.entities.Account
import com.example.demo.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<Account, Long> {
    fun findByUser(user: User): Account

    fun findByUserCpf(cpf: String): Account

    fun existsByUserCpf(cpf: String): Boolean
}