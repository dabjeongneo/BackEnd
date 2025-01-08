package com.example.studykotlin.global.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class JwtProperties(
    //${} 자바와 처리방법이 달라서 이스케이프 처리 \ <- 이걸 붙여야 함
    @Value("\${jwt.header}")
    val header: String,
    @Value("\${jwt.prefix}")
    val prefix: String,
    @Value("\${jwt.access-exp}")
    val accessExp: Long,
    @Value("\${jwt.refresh-exp}")
    val refreshExp: Long,
    @Value("\${jwt.secret}")
    val secret: String
) {

}