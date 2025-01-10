package com.example.studykotlin.domain.auth.controller

import com.example.studykotlin.domain.auth.controller.dto.request.SignInRequest
import com.example.studykotlin.domain.auth.controller.dto.request.SignUpRequest
import com.example.studykotlin.domain.auth.servise.SignInService
import com.example.studykotlin.domain.auth.servise.SignUpService
import com.example.studykotlin.domain.user.domain.User
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val signInService: SignInService,
    val signUpService: SignUpService
) {

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest ): TokenResponse {
        return signInService.excute(request)
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest): TokenResponse {
        return signUpService.excute(request)
    }
}