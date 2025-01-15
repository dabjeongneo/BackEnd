package com.example.studykotlin.domain.test.controller

import com.example.studykotlin.global.exception.UserNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {
    @GetMapping("/user-not-found")
    fun userNotFound(): String {
        throw UserNotFoundException.EXCEPTION
    }
}