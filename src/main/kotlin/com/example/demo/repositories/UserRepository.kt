package com.example.demo.repositories

import com.example.demo.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByCpf(cpf: String): User

    fun existsByCpf(cpf: String): Boolean
}