package com.example.studykotlin.domain.ClubReader.repostiory

import com.example.studykotlin.domain.ClubReader.domain.ClubReader
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClubReaderRepository : CrudRepository<ClubReader,Int> {
    fun findBySchoolId(schoolId: Int): List<ClubReader>?
}