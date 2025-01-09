package com.example.studykotlin.domain.auth.excpetion

import com.example.studykotlin.global.error.exception.DabjeongneoException
import com.example.studykotlin.global.error.exception.ErrorCode

class EmailAlreadyExistException : DabjeongneoException(ErrorCode.EAMIL_ALREADY_EXIST) {
    companion object {
        @JvmStatic
        val  EXCPETION = EmailAlreadyExistException()
    }
}