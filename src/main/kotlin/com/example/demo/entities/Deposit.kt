package com.example.demo.entities

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "deposits")
class Deposit(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "depositor_id")
    val depositor: User? = null,

    val date: Date? = null,

    val value: Long? = null
)