package com.example.studykotlin.global.Auth

import com.example.studykotlin.domain.user.domain.repository.UserRepostiory
import com.example.studykotlin.global.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    val userRepostiory: UserRepostiory
):UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepostiory.findByEmail(username)?: throw UserNotFoundException.EXCEPTION
    }

}