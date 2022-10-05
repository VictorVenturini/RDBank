package com.example.demo.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transfers")
class Transfer(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sender_id")
    val sender: User? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "receiver_id")
    val receiver: User? = null,

    val date: Date? = null,

    val value: Long
)