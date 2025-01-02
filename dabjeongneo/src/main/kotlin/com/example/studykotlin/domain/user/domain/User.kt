package com.example.studykotlin.domain.user.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val email: String,
    val password: String,
    val role: String
    )
 {

 }