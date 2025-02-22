package com.example.studykotlin.domain.question.domain.repository

import com.example.studykotlin.domain.interview_review.domain.Interview_review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface QuestionRepository : JpaRepository<Interview_review,UUID> {
}