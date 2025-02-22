package com.example.studykotlin.domain.admin.domain

import com.example.studykotlin.domain.user.domain.User
import java.util.*
import javax.persistence.*

@Entity
class Admin(

    @Id
    @Column(name = "user_id")
    val id: UUID,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", referencedColumnName = "id")
    val user: User,

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    val name: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val profile_image: String
) {

}