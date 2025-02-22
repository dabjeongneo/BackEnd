package com.example.studykotlin.domain.club.repostiory

import com.example.studykotlin.domain.club.domain.Club
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClubRepository : CrudRepository<Club,UUID> {

}