package com.example.studykotlin.global.jwt

import com.example.studykotlin.global.exception.ExpiredTokenExcpetion
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

        if(isExcloudUrl(request.requestURI)){
            doFilter(request, response, filterChain)
            return
        }
        val token = jwtProvider.revolveToken(request)

        jwtProvider.validateToken(token)

        if(jwtProvider.isAccessTokenLogout(token)){
            print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            throw ExpiredTokenExcpetion.EXCEPTION
        }

        var authentication:Authentication = jwtReissueUtil.getAthentication(token)
        SecurityContextHolder.getContext().authentication = authentication

        doFilter(request,response,filterChain)

    }

    fun isExcloudUrl(url : String):Boolean {
        var regex = "/public/**".replace("**",".*").toRegex()
        return url.matches(regex)
    }

}