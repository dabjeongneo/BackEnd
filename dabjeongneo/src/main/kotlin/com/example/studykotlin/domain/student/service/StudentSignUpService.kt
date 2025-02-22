package com.example.studykotlin.domain.student.service

import com.example.studykotlin.domain.student.controller.dto.request.StudentSignUpRequest
import com.example.studykotlin.domain.auth.facade.UserFacade
import com.example.studykotlin.domain.student.domain.Student
import com.example.studykotlin.domain.student.domain.repository.StudentRepository
import com.example.studykotlin.domain.user.domain.User
import com.example.studykotlin.domain.user.domain.repository.UserRepository
import com.example.studykotlin.domain.user.domain.type.Role
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class StudentSignUpService(
    private val userFacade: UserFacade,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val studentRepository: StudentRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun excute(request: StudentSignUpRequest): TokenResponse {
        userFacade.checkByEmail(request.email)

        val user = userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                role = Role.STUDENT,
                create_at = LocalDateTime.now()
            )
        )

        studentRepository.save(
            Student(
                user = user,
                name = request.name,
                schoolNumber = request.schoolNumber,
                profileImage = "임시"
            )
        )

        return jwtProvider.createToken(request.email)
    }
}