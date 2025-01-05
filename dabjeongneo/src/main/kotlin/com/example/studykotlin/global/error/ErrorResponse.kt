package com.example.studykotlin.global.error

import com.example.studykotlin.global.error.exception.ErrorCode

class ErrorResponse private constructor(
    val status: Int,
    val message: String
){
    fun of(status: Int,message: String): ErrorResponse {
        return ErrorResponse(status, message)
    }

    fun of(errorCode: ErrorCode): ErrorResponse {
        return ErrorResponse(errorCode.status,errorCode.message)
    }
}