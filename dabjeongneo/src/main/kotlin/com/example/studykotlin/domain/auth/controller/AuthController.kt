package com.example.studykotlin.domain.auth.controller

import com.example.studykotlin.domain.auth.controller.dto.request.ReissueRequest
import com.example.studykotlin.domain.auth.controller.dto.request.SignInRequest
import com.example.studykotlin.domain.auth.controller.dto.request.SignUpRequest
import com.example.studykotlin.domain.auth.domain.refreshToken.RefreshToken
import com.example.studykotlin.domain.auth.servise.LogoutService
import com.example.studykotlin.domain.auth.servise.ReissueService
import com.example.studykotlin.domain.auth.servise.SignInService
import com.example.studykotlin.domain.auth.servise.SignUpService
import com.example.studykotlin.global.jwt.JwtReissueUtil
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.apache.el.parser.Token
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class AuthController(
    val signInService: SignInService,
    val signUpService: SignUpService,
    val logoutService: LogoutService,
    val reissueService: ReissueService
) {

    @PostMapping("public/signin")
    fun signIn(@RequestBody request: SignInRequest ): TokenResponse {
        return signInService.excute(request)
    }

    @PostMapping("public/signup")
    fun signUp(@RequestBody request: SignUpRequest): TokenResponse {
        return signUpService.excute(request)
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest){
        logoutService.excute(request)
    }

    @PostMapping("public/reissue") // public 으로 하면 안됨
    fun reissue(@RequestBody reissueRequest: ReissueRequest): TokenResponse{
        return reissueService.excute(reissueRequest)
    }
}