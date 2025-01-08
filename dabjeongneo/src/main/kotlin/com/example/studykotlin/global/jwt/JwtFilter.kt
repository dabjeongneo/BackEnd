package com.example.studykotlin.global.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class JwtFilter(
    val jwtProvider: JwtProvider,
    val jwtReissueUtil: JwtReissueUtil
): OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val token = jwtProvider.revolveToken(request)
        jwtProvider.validateToken(token)

        var authentication:Authentication = jwtReissueUtil.getAthentication(token)
        SecurityContextHolder.getContext().authentication = authentication

        doFilter(request,response,filterChain)

    }
}