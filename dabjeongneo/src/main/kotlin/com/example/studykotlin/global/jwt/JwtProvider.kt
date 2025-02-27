package com.example.studykotlin.global.jwt

import com.example.studykotlin.domain.auth.servise.RedisService
import com.example.studykotlin.global.exception.ExpiredTokenExcpetion
import com.example.studykotlin.global.exception.InvalidTokenExcpetion
import com.example.studykotlin.global.jwt.response.TokenResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class JwtProvider(
    val jwtProperties: JwtProperties,
    val redisService: RedisService
) {
    fun createToken(username: String): TokenResponse {

        val accessToken = createAccessToken(username)
        val refreshToken = createRefreshToken(username)
        redisService.save(accessToken, refreshToken, jwtProperties.accessExp)

        return TokenResponse(accessToken, refreshToken)
    }

    fun createAccessToken(username: String): String {
        val now: Date = Date()
        val accessToken = Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.accessExp * 1000))
            .compact()

        return accessToken
    }

    fun createRefreshToken(username: String): String {
        val now: Date = Date()
        val refreshToken = Jwts.builder()
            .claim("type", "refresh")
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.refreshExp * 1000))
            .compact()

        redisService.save(refreshToken, "refresh", jwtProperties.refreshExp)

        return refreshToken
    }


    fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(jwtProperties.header)?.also {
            if (it.startsWith(jwtProperties.prefix)) {
                return it.substring(jwtProperties.prefix.length).trim()
            }
        }


    fun parseToken(token: String): Jws<Claims> {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.secret).parseClaimsJws(token)
        } catch (ex: Exception) {
            throw InvalidTokenExcpetion.EXCEPTION
        }

    }

    fun getBody(claims: Jws<Claims>): Claims {
        return claims.body
    }

    fun validateToken(token: String) {
        val claims = getBody(parseToken(token))

        if (claims.expiration.before(Date())) {
            throw ExpiredTokenExcpetion.EXCEPTION
        }
    }

    fun checkAccessTokenLogout(token: String) {
        if (redisService.getValueByKey("$token for black list") != null){
            throw ExpiredTokenExcpetion.EXCEPTION
        }
    }

    fun isRefreshTokenExpired(refreshToken: String): Boolean {
        if (redisService.getValueByKey(refreshToken) == null) {
            return true
        } else {
            return false
        }
    }
}
