package com.example.studykotlin.domain.club.domain

import com.example.studykotlin.domain.user.domain.User
import java.util.UUID
import javax.persistence.*

@Entity
class Club(

    @Id
    @Column(name = "user_id")
    val id: UUID,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", referencedColumnName = "id")
    val user: User,

    @Column(columnDefinition = "VARCHAR(36)", nullable = false)
    val clubName: String,

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    val logoImage: String,

    @Column(columnDefinition = "TEXT", nullable = true)
    val club_explanation: String,
) {

}