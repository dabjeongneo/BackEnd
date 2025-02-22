package com.example.studykotlin.domain.auth.controller.dto.request

import javax.validation.constraints.NotBlank

class ReissueRequest(
    @field:NotBlank
    val refreshToken: String
) {

}