package com.example.studykotlin.domain.InterviewQuestion.domain.repository

import com.example.studykotlin.domain.InterviewQuestion.domain.InterviewQuestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InterviewQuestionRepository : JpaRepository<InterviewQuestion,Long> {
}