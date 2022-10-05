package com.example.demo.entities

import javax.persistence.*

@Entity
@Table(name = "accounts")
class Account(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val user: User? = null,

    var balance: Long? = null
)