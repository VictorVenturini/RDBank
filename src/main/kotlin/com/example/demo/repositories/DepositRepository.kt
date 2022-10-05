package com.example.demo.repositories

import com.example.demo.entities.Deposit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepositRepository: JpaRepository<Deposit, Long> {
}