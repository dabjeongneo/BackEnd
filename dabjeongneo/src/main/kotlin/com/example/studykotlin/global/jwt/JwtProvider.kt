package com.example.studykotlin.global.jwt

import org.springframework.stereotype.Component

@Component
class JwtProvider(
    val jwtPropoties: JwtPropoties
) {

}