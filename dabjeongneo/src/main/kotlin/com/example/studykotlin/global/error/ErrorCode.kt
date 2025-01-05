package com.example.studykotlin.global.error

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    USERNAME_NOT_FOUND(404,"Username not found")
}