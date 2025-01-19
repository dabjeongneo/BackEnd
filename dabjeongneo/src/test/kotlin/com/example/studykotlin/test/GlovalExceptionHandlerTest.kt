package com.example.studykotlin.test

import com.example.studykotlin.test.testcontroller.TestController
import com.example.studykotlin.global.error.GlobalExceptionHandler
import com.example.studykotlin.global.error.exception.ErrorCode
import com.example.studykotlin.global.jwt.JwtProvider
import com.example.studykotlin.global.jwt.JwtReissueUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

// tset 시 MVC 관련 컴포넌트를 메모리에 업로드
@WebMvcTest(TestController::class) // 범위를 TestController 클래스만으로 함
@Import(GlobalExceptionHandler::class)
class GlovalExceptionHandlerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc // HTTP 요청을 보내고 응답을 확인하는데 사용됨
            // 나중에 초기화 되지만, 꼭 초기화될 객체(null 처리 안해도 됨.)

    @MockBean
    private lateinit var jwtProvider: JwtProvider

    @MockBean
    private lateinit var jwtReissueUtil: JwtReissueUtil

    @Test
    @WithMockUser(username = "test1", roles = ["USER"], password = "1111") //given
    fun `shold return status 404 and message User_Not_found`(){

        //when
        mockMvc.get("/test/user-not-found") // GET 요청을 이 url 로 보냄
            .andExpect {
                status { isNotFound() } // 실제 HTTP 상태코드가 isNotFound() 인지


                //then
                // $ 는 json 객체의 루트를 의미
                jsonPath("$.status") { value(ErrorCode.USER_NOT_FOUND.status) }
                jsonPath("$.message") { value(ErrorCode.USER_NOT_FOUND.message) }
            }
    }


}