package com.example.studykotlin.domain.student.domain.repository

import com.example.studykotlin.domain.student.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StudentRepository : JpaRepository<Student, UUID> {
}