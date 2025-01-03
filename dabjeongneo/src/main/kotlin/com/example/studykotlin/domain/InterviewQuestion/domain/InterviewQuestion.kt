package com.example.studykotlin.domain.InterviewQuestion.domain

import com.example.studykotlin.domain.user.domain.User
import javax.persistence.*

@Entity
class InterviewQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,
    var question: String? = null,
) {
}