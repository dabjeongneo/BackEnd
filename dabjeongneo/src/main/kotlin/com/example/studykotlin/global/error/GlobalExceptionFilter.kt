package com.example.studykotlin.global.error


import com.example.studykotlin.global.error.exception.DabjeongneoException
import com.fasterxml.jackson.databind.ObjectMapper
import com.mysql.cj.log.LogFactory
import lombok.extern.slf4j.Slf4j
import org.springframework.web.filter.OncePerRequestFilter
import java.util.logging.Logger
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Slf4j
class GlobalExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        try {
            filterChain.doFilter(request, response)
        } catch (e: DabjeongneoException) {
            val errorCode = e.errorCode
            print(errorCode)
            writeErrorResponse(response, errorCode.status, ErrorResponse.of(errorCode.status, errorCode.message))
        } catch (e: Exception) {
            writeErrorResponse(response, response.status, ErrorResponse.of(response.status, e.message))
        }
    }

    private fun writeErrorResponse(response: HttpServletResponse, statusCode: Int, errorResponse: ErrorResponse) {
        response.status = statusCode
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        objectMapper.writeValue(response.writer, errorResponse)
    }
}
