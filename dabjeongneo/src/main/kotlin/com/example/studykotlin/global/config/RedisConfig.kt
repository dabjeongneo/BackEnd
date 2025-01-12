package com.example.studykotlin.global.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Value("\${spring.redis.port}")
    val port: Int? = null

    @Value("\${spring.redis.host}")
    val host: String? = null

    @Value("\${spring.redis.password}")
    val password: String? = null

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redisConfig = RedisStandaloneConfiguration(host,port!!)
        redisConfig.setPassword(password)
        return LettuceConnectionFactory(redisConfig)
    }

    @Bean           // 스프링이 redisConnextionFactory 가 Bean 으로 설정됬다고 가정하고 파라미터로 넣어줌
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, String> {
        var template = RedisTemplate<String,String>()
        template.connectionFactory = redisConnectionFactory

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        return template
    }

}