package com.example.studykotlin.domain.auth.servise

import com.example.studykotlin.domain.auth.controller.dto.request.SignUpRequest
import com.example.studykotlin.domain.auth.facade.UserFacade
import com.example.studykotlin.domain.user.domain.User
import com.example.studykotlin.domain.user.domain.repository.UserRepostiory
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.stereotype.Service

@Service
class SignUpService(
    val userFacade: UserFacade,
    val userRepostiory: UserRepostiory,
    val jwtProvider: JwtProvider
) {

    fun excute(request: SignUpRequest): TokenResponse{
        userFacade.checkByEmail(request.email)
        userFacade.checkBySchoolId(request.schoolId)

        userRepostiory.save(User(
            email = request.email,
            schoolId = request.schoolId,
            password = request.password
        ))

        return jwtProvider.createToken(request.email)

    }
}