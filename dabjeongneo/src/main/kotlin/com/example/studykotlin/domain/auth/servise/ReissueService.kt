package com.example.studykotlin.domain.auth.servise

import com.example.studykotlin.domain.auth.controller.dto.request.ReissueRequest
import com.example.studykotlin.global.jwt.JwtProperties
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.JwtReissueUtil
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class ReissueService(
    val jwtReissueUtil: JwtReissueUtil,
    val jwtProvider: JwtProvider
) {
    fun excute(reissueRequest: ReissueRequest):TokenResponse{
        return jwtReissueUtil.tokenReissueByRefreshToken(reissueRequest.refreshToken)
    }
}