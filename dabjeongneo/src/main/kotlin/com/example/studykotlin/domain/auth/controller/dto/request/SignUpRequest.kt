package com.example.studykotlin.domain.auth.controller.dto.request


import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class SignUpRequest(

//    @NotBlank(message = "email 은 null 이거나 공백, 띄어쓰기를 허용하지 않습니다.")
//    @Email(message = "email 형식에 맞추어 써주세요")
    val email: String,

//    @NotBlank(message = "password 는 null 이거나 공백, 띄어쓰기를 허용하지 않습니다.")
//    @Size(min = 8, message = "password 는 8자 이상이여야 합니다.")
    val password: String,

    val schoolId: Int
) {
}