package com.example.studykotlin.global.config

import com.example.studykotlin.global.error.GlobalExceptionFilter
import com.example.studykotlin.global.jwt.JwtFilter
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.JwtReissueUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtProvider: JwtProvider,
    private val jwtReissueUtil: JwtReissueUtil,
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val jwtFilter = JwtFilter(jwtProvider, jwtReissueUtil)
        val globalExceptionFilter = GlobalExceptionFilter(objectMapper)

        http
            .csrf().disable()
            .cors()
            .and()
            .exceptionHandling()
            .and()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .antMatchers("/CLUBLEADER/**").hasRole("CLUBLEADER")
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight 요청 허용
            .antMatchers("/**").permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler { _, response, _ ->
                response.contentType = "application/json"
                response.writer.write("{\"message\": \"Logout successful\"}")
                response.status = HttpServletResponse.SC_OK
            }
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(globalExceptionFilter, JwtFilter::class.java)

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("OPTIONS", "GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("*")
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
