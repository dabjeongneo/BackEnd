package com.example.studykotlin.domain.question.domain

import com.example.studykotlin.domain.Interview.domain.Interview
import com.example.studykotlin.domain.question.domain.type.QuestionPattern
import com.example.studykotlin.domain.question.domain.type.QuestionType
import org.hibernate.annotations.GenericGenerator
import java.util.UUID
import javax.persistence.*

@Entity
class Question (

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id", nullable = false)
    val interview: Interview,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)",nullable = false)
    val questiontype: QuestionType,

    @Column(columnDefinition = "VARHCAR(255)",nullable = false)
    val questionTitle: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val detail: String,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "question_keywords", joinColumns = [JoinColumn(name = "Question_id")])
    @Column(nullable = false)
    val keyword: List<String>,

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    val questionPattern: QuestionPattern,

    @Column(columnDefinition = "BIT(1)")
    val oxAnswer: Boolean?
) {

}