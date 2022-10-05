package com.example.demo.repositories

import com.example.demo.entities.Transfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository: JpaRepository<Transfer, Long> {
}