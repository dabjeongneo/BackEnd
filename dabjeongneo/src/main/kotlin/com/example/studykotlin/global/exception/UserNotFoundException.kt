package com.example.studykotlin.global.exception

import com.example.studykotlin.global.error.exception.DabjeongneoException
import com.example.studykotlin.global.error.exception.ErrorCode

class UserNotFoundException : DabjeongneoException(ErrorCode.USER_NOT_FOUND) {
    companion object {
        @JvmStatic
        val EXCEPTION = UserNotFoundException()
    }
}