package com.example.studykotlin.global.jwt

import com.example.studykotlin.global.Auth.UserDetailsService
import com.example.studykotlin.global.exception.InvalidTokenExcpetion
import com.example.studykotlin.global.jwt.response.TokenResponse
import io.jsonwebtoken.Claims
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class JwtReissueUtil(
    val jwtProvider: JwtProvider,
    val jwtProperties: JwtProperties,
    private val userDetailsService: UserDetailsService
) {
    fun parseAndgetBodyToken(token: String):Claims {
        return jwtProvider.getBody(jwtProvider.parseToken(token))
    }

    fun tokenReissueByRefreshToken(refreshToken: String): TokenResponse{

        if((!isRefreshToken(refreshToken)) || jwtProvider.isRefreshTokenExpired(refreshToken)){
            throw InvalidTokenExcpetion.EXCEPTION
        }

        return TokenResponse(
            jwtProvider.createAccessToken(getUsername(refreshToken)),
            refreshToken
        )

    }

    fun isRefreshToken(refreshToken: String): Boolean{
        return parseAndgetBodyToken(refreshToken).get("type")?.equals("refresh") ?: false
    }

    fun getUsername(refreshToken: String): String{
        return parseAndgetBodyToken(refreshToken).subject
    }

    fun getAthentication(refreshToken: String):Authentication {
        val user = userDetailsService.loadUserByUsername(getUsername(refreshToken))

        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }

}