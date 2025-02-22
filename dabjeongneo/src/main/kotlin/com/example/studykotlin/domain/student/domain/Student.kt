package com.example.studykotlin.domain.student.domain

import com.example.studykotlin.domain.user.domain.User
import java.util.*
import javax.persistence.*


@Entity
class Student(

    @Id
    @Column(name = "user_id")
    val id: UUID? = null,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", referencedColumnName = "id")
    val user: User,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val name: String,

    @Column(nullable = false)
    val schoolNumber: Int,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val profileImage: String,
) {

}