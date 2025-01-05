package com.example.studykotlin.global.error

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    USER_NOT_FOUND(404,"User not found")
}