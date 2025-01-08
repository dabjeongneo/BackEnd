package com.example.studykotlin.global.exception

import com.example.studykotlin.global.error.exception.DabjeongneoException
import com.example.studykotlin.global.error.exception.ErrorCode

class InvalidTokenExcpetion : DabjeongneoException(ErrorCode.INVALID_TOKEN) {
    companion object{
        @JvmStatic
        val EXCEPTION = InvalidTokenExcpetion()
    }
}