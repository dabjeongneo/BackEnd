package com.example.studykotlin.global.jwt

import com.example.studykotlin.global.exception.InvalidTokenExcpetion
import com.example.studykotlin.global.jwt.response.TokenResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtProvider(
    val jwtProperties: JwtProperties
) {
    fun createToken(username: String): TokenResponse {
        return TokenResponse(
            createAccessToken(username),
            createRefreshToken(username)
        )

    }

    fun createAccessToken(username: String): String {
        val now: Date = Date()
        val accessToken= Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256,jwtProperties.secret)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.accessExp*1000))
            .compact()

        return accessToken
    }

    fun createRefreshToken(username: String): String {
        val now: Date = Date()
        val refreshToken= Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + jwtProperties.refreshExp*1000))
            .compact()
        return refreshToken
    }

    fun revolveToken(request: HttpServletRequest): String{
        val bearerToken = request.getHeader(jwtProperties.header)

        if(bearerToken != null && bearerToken.startsWith(jwtProperties.prefix) && bearerToken.length > jwtProperties.prefix.length){

            return bearerToken.substring(jwtProperties.prefix.length).trim()//trim() 앞뒤 공백 제거

        }else{
            throw InvalidTokenExcpetion.EXCEPTION
        }
    }

    fun parseToken(token:String): Jws<Claims> {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.secret).parseClaimsJws(token)
        }catch (ex:Exception){
            throw InvalidTokenExcpetion.EXCEPTION
        }

    }

    fun getBody(claims: Jws<Claims>):Claims {
        return claims.body
    }

    fun validateToken(token:String){
        val claims = getBody(parseToken(token))

        if(claims.expiration.before(Date())) {
            throw InvalidTokenExcpetion.EXCEPTION
        }
    }

}