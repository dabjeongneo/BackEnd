package com.example.studykotlin.domain.auth.controller.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignInRequest(
    val email: String,

    val password: String
) {
}