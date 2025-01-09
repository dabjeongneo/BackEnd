package com.example.studykotlin.global.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun SecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .csrf()
            .disable()
            .cors()
            .and()
            .exceptionHandling()
            .and()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll() //cors의 preflight 요청 를 위한 option 요청 허용설정
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and()
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        var configuration = CorsConfiguration()
        configuration.allowedOrigins = mutableListOf("*")
        configuration.allowedMethods = mutableListOf("OPTION","GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = mutableListOf("*")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }



    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
