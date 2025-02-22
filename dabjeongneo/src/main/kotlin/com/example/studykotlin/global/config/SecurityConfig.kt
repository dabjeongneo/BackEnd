package com.example.studykotlin.global.config

import com.example.studykotlin.domain.user.domain.type.Role
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
            .authorizeHttpRequests() { authorize ->
                authorize
                        // /auth
                    .antMatchers("auth/signin").permitAll()
                    .antMatchers("auth/reissue").permitAll()
                    .antMatchers("auth/logout").hasAnyAuthority(Role.STUDENT.name, Role.ADMIN.name, Role.CLUBREDEAR.name)
                        // /students
                    .antMatchers("students/signup").permitAll()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Preflight 요청 허용
            }
        http
            .logout()
            .logoutUrl("/this-is-just-return-logout-successful") // 로그아웃 요청 경로
            .logoutSuccessHandler { _, response, _ ->
                response.status = HttpServletResponse.SC_OK // 로그아웃 성공 시 200 상태 코드만 반환
                response.writer.write("{\"message\": \"Logout successful\"}") // 응답 메시지
                response.contentType = "application/json"
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
