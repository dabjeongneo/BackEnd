package com.example.studykotlin.global.config

import com.example.studykotlin.global.error.GlobalExceptionFilter
import com.example.studykotlin.global.jwt.JwtFilter
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.JwtReissueUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


class FilterConfig(
    val jwtProvider: JwtProvider,
    val jwtReissueUtil: JwtReissueUtil,
    val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(http: HttpSecurity) {

        var jwtFilter = JwtFilter(jwtProvider , jwtReissueUtil)
        var globalExcpetionFilter =  GlobalExceptionFilter(objectMapper)

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(globalExcpetionFilter, JwtFilter::class.java)


    }
}