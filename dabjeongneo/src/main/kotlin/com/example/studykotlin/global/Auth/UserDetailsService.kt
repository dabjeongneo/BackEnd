package com.example.studykotlin.global.Auth

import com.example.studykotlin.domain.user.domain.repository.UserRepostiory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService(
    val userRepostiory: UserRepostiory
):UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepostiory.findByEmail(username)?: throw UsernameNotFoundException("User $username not found")
    }

}