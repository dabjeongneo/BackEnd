package com.example.studykotlin.domain.InterviewQuestion.service


import com.example.studykotlin.domain.InterviewQuestion.controller.dto.CreateInterviewQRequest
import com.example.studykotlin.domain.InterviewQuestion.domain.InterviewQuestion
import com.example.studykotlin.domain.InterviewQuestion.domain.repository.InterviewQuestionRepository
import com.example.studykotlin.domain.user.domain.User
import org.springframework.stereotype.Service

@Service
class CreateInterviewQuestion (
    val repository: InterviewQuestionRepository
){
//    fun createInterviewQuestion(createInterviewQRequest: CreateInterviewQRequest): Unit{
//
//        //var user: facade.currentUser() 같은 메서드로 현재 유저 가져오기.
//        var interviewQuestion: InterviewQuestion = InterviewQuestion(0,/*User 자리*/ ,createInterviewQRequest.question)
//
//        repository.save(interviewQuestion);
//    }

}