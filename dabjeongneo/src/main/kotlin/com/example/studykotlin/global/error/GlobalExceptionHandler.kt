package com.example.studykotlin.global.error

import com.example.studykotlin.global.error.exception.DabjeongneoException
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(DabjeongneoException::class) // :: 는 참조를 가르킴 답정너exception 클래스 정보를 넘김
    fun BessnesExceptionHandler(exception: DabjeongneoException): ResponseEntity<ErrorResponse> { //ErrorResponse 객체를 본문(첫번째 매게변수) 에 띄움
        val errorRespone = ErrorResponse.of(exception.errorCode)
        logger.error("DabjeongneoException occurred", errorRespone)
        return ResponseEntity(errorRespone,HttpStatus.valueOf(errorRespone.status))
    }

    @ExceptionHandler(ConstraintViolationException::class) // validation 에서 오류난것들
    fun ConstraintViolationExceptionHandler(exception: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(400,"validation error")
        logger.error("Dabjeongneo occurred ",exception)
        return ResponseEntity(errorResponse,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun ExceptionHandler(exception: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.of(500,"server error")
        logger.error("Exception occurred ",exception)
        return ResponseEntity(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR)
    }
}