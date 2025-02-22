package com.example.studykotlin.domain.student.controller

import com.example.studykotlin.domain.student.controller.dto.request.StudentSignUpRequest
import com.example.studykotlin.domain.student.service.StudentSignUpService
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController(
    val studentSignUpService: StudentSignUpService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: StudentSignUpRequest): TokenResponse {
        return studentSignUpService.excute(request)
    }
}
