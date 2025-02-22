package com.example.studykotlin.domain.question.service


import com.example.studykotlin.domain.question.domain.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class CreateInterviewQuestion (
    val repository: QuestionRepository
){
//    fun createInterviewQuestion(createInterviewQRequest: CreateInterviewQRequest): Unit{
//
//        //var user: facade.currentUser() 같은 메서드로 현재 유저 가져오기.
//        var interviewQuestion: InterviewQuestion = InterviewQuestion(0,/*User 자리*/ ,createInterviewQRequest.question)
//
//        repository.save(interviewQuestion);
//    }

}