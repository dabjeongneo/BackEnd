package com.example.studykotlin.global.jwt

import com.example.studykotlin.global.exception.ExpiredTokenExcpetion
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
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = jwtProvider.resolveToken(request)

        token?.let {
            jwtProvider.validateToken(token)
            jwtProvider.checkAccessTokenLogout(token)
            SecurityContextHolder.getContext().authentication = jwtReissueUtil.getAthentication(token)
        }

        doFilter(request, response, filterChain)

    }
}