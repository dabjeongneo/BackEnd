package com.example.studykotlin.domain.ClubReader.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ClubReader(
    @Id
    val schoolId: Int,
) {
}