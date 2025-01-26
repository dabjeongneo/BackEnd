package com.example.studykotlin.domain.user.domain.repository


import com.example.studykotlin.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User,Long> {
    fun findByEmail(email: String): User?
    fun findBySchoolId(schoolId: Int): User?
}