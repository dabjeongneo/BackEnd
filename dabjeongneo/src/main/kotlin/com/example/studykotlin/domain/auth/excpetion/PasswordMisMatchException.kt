package com.example.studykotlin.domain.auth.excpetion

import com.example.studykotlin.global.error.exception.DabjeongneoException
import com.example.studykotlin.global.error.exception.ErrorCode

class PasswordMisMatchException : DabjeongneoException(ErrorCode.PASSWORD_MISSMATCH) {

    companion object{
        @JvmStatic
        val EXCEPTION = PasswordMisMatchException()
    }
}