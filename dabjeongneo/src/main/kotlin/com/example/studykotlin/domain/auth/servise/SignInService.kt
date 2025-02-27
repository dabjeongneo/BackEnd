package com.example.studykotlin.domain.auth.servise

import com.example.studykotlin.domain.auth.controller.dto.request.SignInRequest
import com.example.studykotlin.domain.auth.excpetion.PasswordMisMatchException
import com.example.studykotlin.domain.user.domain.repository.UserRepository
import com.example.studykotlin.global.exception.UserNotFoundException
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignInService(
    val jwtProvider: JwtProvider,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {

    fun excute(request: SignInRequest): TokenResponse {

        val user = userRepository.findByEmail(request.email)
        if (user == null) {
            throw UserNotFoundException.EXCEPTION
        }

        print("111111111${request.password} " +
                "2222222222${user.password}")

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw PasswordMisMatchException.EXCEPTION
        }

        return jwtProvider.createToken(request.email)
    }
}