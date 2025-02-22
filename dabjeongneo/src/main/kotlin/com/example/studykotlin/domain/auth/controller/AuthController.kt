package com.example.studykotlin.domain.auth.controller

import com.example.studykotlin.domain.auth.controller.dto.request.ReissueRequest
import com.example.studykotlin.domain.auth.controller.dto.request.SignInRequest
import com.example.studykotlin.domain.auth.servise.LogoutService
import com.example.studykotlin.domain.auth.servise.ReissueService
import com.example.studykotlin.domain.auth.servise.SignInService
import com.example.studykotlin.domain.student.service.StudentSignUpService
import com.example.studykotlin.global.jwt.response.TokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/auth")
class AuthController(
    val signInService: SignInService,
    val logoutService: LogoutService,
    val reissueService: ReissueService
) {

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInRequest): TokenResponse {
        return signInService.excute(request)
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest) {
        logoutService.excute(request)
    }

    @PostMapping("/reissue") // public 으로 하면 안됨
    fun reissue(@RequestBody reissueRequest: ReissueRequest): TokenResponse {
        return reissueService.excute(reissueRequest)
    }
}