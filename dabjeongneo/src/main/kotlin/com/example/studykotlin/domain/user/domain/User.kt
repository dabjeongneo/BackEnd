package com.example.studykotlin.domain.user.domain


import com.example.studykotlin.domain.InterviewQuestion.domain.InterviewQuestion
import javax.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val email: String,
    val password: String,
    val role: String,
    @OneToMany
    var interviewQuestions: MutableList<InterviewQuestion> = mutableListOf(),

    )
 {

 }