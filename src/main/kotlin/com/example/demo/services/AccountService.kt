package com.example.demo.services

import br.com.colman.simplecpfvalidator.isCpf
import com.example.demo.entities.Account
import com.example.demo.entities.User
import com.example.demo.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.webjars.NotFoundException
import java.lang.IllegalArgumentException

@Service
class AccountService {
    @Autowired
    lateinit var accountRepository: AccountRepository

    fun createAccount(user: User): Account {
        if (!accountRepository.existsByUserCpf(user.cpf)) {
            if (user.cpf.isCpf(charactersToIgnore = listOf('.', '/'))) {
                val account = Account(
                    user = user,
                    balance = 0
                )
                user.account = account
                return accountRepository.save(account)
            } else {
                throw IllegalArgumentException("O cpf inserido é invalido")
            }
        } else {
            throw IllegalArgumentException("O cpf inserido já está cadastrado")
        }
    }

    fun findByCpf(cpf: String): Account {
        if (accountRepository.existsByUserCpf(cpf)) {
            return accountRepository.findByUserCpf(cpf)
        } else {
            throw NotFoundException("O cpf inserido não foi encontrado")
        }
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}