package com.example.studykotlin.domain.auth.domain.refreshToken

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash
class RefreshToken(
    @Id
    val RefreshToken:String,
    var status: String
) {
}