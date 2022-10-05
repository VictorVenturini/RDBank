package com.example.demo.services

import com.example.demo.entities.User
import com.example.demo.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}