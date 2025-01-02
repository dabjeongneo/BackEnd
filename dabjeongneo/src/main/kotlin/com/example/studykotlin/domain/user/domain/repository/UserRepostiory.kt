package com.example.studykotlin.domain.user.domain.repository


import com.example.studykotlin.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepostiory: JpaRepository<User,Long> {
}