package com.example.studykotlin.domain.user.domain.repository


import com.example.studykotlin.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User,UUID> {
    fun findByEmail(email: String): User?
}