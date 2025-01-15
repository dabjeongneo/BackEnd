package com.example.studykotlin.test

import com.example.studykotlin.global.config.SecurityConfig
import com.example.studykotlin.global.jwt.JwtProperties
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var accessToken:String
    private lateinit var refreshToken:String


    @BeforeEach
    fun `Jwt setup`(){
        val mvcResult = mockMvc.post("/public/signup"){
            contentType = MediaType.APPLICATION_JSON
            content = """{"email":"test","password":"test","schoolId":"1112"}"""

        }.andExpect {
            status { isOk() }
        }.andDo { print() }
         .andReturn()
                                                        //json 값을 key-value 를 한쌍으로 노드에 담아 트리에 저장함

        val jsonResponse = mvcResult.response.contentAsString
        val jsonNode = jacksonObjectMapper.readTree(jsonResponse)

        accessToken = jsonNode.get("access_token").asText()
        refreshToken = jsonNode.get("refresh_token").asText()
    }


    @Test
    fun `A logged out user cannot access APIs except those starting with 'public' and cannot reissue using a refreshtoken`(){
        //given
        mockMvc.post("/logout"){
            header("Authorization", "Bearer $accessToken")
        }.andExpect {
            status { isOk() }
        }

        //when
        mockMvc.post("/logout"){
            header("Authorization", "Bearer $accessToken")
        }.andExpect {
            //then
            status { isUnauthorized() }
        }
        //when
        mockMvc.post("public/reissue"){
            contentType = MediaType.APPLICATION_JSON
            content = """{refreshToken:"$refreshToken"}"""
        }

    }


}