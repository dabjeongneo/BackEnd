package com.example.studykotlin.global.error

class UserNotFoundException : DabjeongneoException(ErrorCode.USERNAME_NOT_FOUND) {
    companion object {
        @JvmStatic
        val EXCEPTION = UserNotFoundException()
    }
}