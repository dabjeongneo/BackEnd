package com.example.studykotlin.domain.auth.servise

import com.example.studykotlin.global.exception.InvalidTokenExcpetion
import com.example.studykotlin.global.jwt.JwtProvider
import org.springframework.stereotype.Component
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class LogoutService(
    val redisService: RedisService,
    val jwtProvider: JwtProvider
) {
    fun excute(request: HttpServletRequest){

        val accessToken = jwtProvider.resolveToken(request)?: throw InvalidTokenExcpetion.EXCEPTION
        // 만료까지 남은시간 구하기 = 엑세스만료시간 - 현재시간

        val expiration = jwtProvider.getBody(jwtProvider.parseToken(accessToken)).expiration
        var now = Date()
        redisService.save("$accessToken for black list","logout",(expiration.time - now.time))

        val refreshToken = redisService.getValueByKey(accessToken)?:throw RuntimeException()
        redisService.deleteByKey(refreshToken) // redis 에 저장되어 있던 refreshToken 삭제
    }

}