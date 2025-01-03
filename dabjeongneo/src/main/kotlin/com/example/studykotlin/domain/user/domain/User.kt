package com.example.studykotlin.domain.user.domain


import com.example.studykotlin.domain.InterviewQuestion.domain.InterviewQuestion
import javax.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String,
    val password: String,
    val role: String,
    @OneToMany(mappedBy = "user") // mappedBy 쓴클래스가 조원(안쓴쪽이 주장) 조원은 조회밖에 못함.
    var interviewQuestions: MutableList<InterviewQuestion> = mutableListOf()
    )
 {

 }