package com.example.studykotlin.domain.auth.servise

import com.example.studykotlin.global.Auth.UserDetailsService
import com.example.studykotlin.global.jwt.JwtProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class LogoutService(
    val redisService: RedisService,
    val jwtProvider: JwtProvider
) {
    fun excute(request: HttpServletRequest){

        val accessToken = jwtProvider.revolveToken(request)
        // 만료까지 남은시간 구하기 = 현재시간 - 엑세스 만료시간
        val expiration = jwtProvider.getBody(jwtProvider.parseToken(accessToken)).expiration
        var now = Date()
        redisService.save(accessToken,"logout",(now.time - expiration.time))

        val refreshToken = redisService.getValueByKey(accessToken)
        redisService.deleteByKey(refreshToken!!) // redis 에 저장되어 있던 refreshToken 삭제
    }

}