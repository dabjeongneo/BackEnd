package com.example.studykotlin.domain.student.controller.dto.request


import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


class StudentSignUpRequest(

    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val name: String,

    @field:NotNull
    @field:Size(min = 4, max = 4)
    val schoolNumber: Int
) {

}