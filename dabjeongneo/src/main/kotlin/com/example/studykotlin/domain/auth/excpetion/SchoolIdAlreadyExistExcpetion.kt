package com.example.studykotlin.domain.auth.excpetion

import com.example.studykotlin.global.error.exception.DabjeongneoException
import com.example.studykotlin.global.error.exception.ErrorCode

class SchoolIdAlreadyExistExcpetion:DabjeongneoException(ErrorCode.SCHOOL_ID_ALREADY_EXIST) {
    companion object {
        @JvmStatic
        val EXCPETION = SchoolIdAlreadyExistExcpetion()
    }
}