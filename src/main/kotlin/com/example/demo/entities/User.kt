package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User(
    @field:Id
    @Size(min = 11, max = 14)
    val cpf: String,

    val name: String,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "account_id")
    @JsonIgnore
    var account: Account? = null
)