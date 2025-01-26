package com.example.studykotlin.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import javax.validation.Validator

@Configuration
class ValidationConfig {

    @Bean
    fun validator(): Validator = LocalValidatorFactoryBean()
}