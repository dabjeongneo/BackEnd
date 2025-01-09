package com.example.studykotlin.domain.auth.controller.dto.request

class SignUpRequest(
    val email: String,
    val password: String,
    val schoolId: Int
) {
}