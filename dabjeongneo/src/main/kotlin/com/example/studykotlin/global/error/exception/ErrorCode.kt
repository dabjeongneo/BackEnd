package com.example.studykotlin.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    USER_NOT_FOUND(404,"User not found"),
    INVALID_TOKEN(401,"Authorization token is Invalid or missing"),
    EXPIRED_TOKEN(401,"Token expired"),
    EAMIL_ALREADY_EXIST(409,"email already exists"),
    SCHOOL_ID_ALREADY_EXIST(409,"school id already exists"),
    PASSWORD_MISSMATCH(400,"password miss match"),

}