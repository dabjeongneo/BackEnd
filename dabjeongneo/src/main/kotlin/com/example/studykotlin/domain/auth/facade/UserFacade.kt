package com.example.studykotlin.domain.auth.facade

import com.example.studykotlin.domain.ClubReader.repostiory.ClubReaderRepository
import com.example.studykotlin.domain.auth.excpetion.SchoolIdAlreadyExistExcpetion
import com.example.studykotlin.domain.user.domain.repository.UserRepostiory
import org.springframework.stereotype.Service

@Service
class UserFacade(
    val userRepostiory: UserRepostiory,
    val clubReaderRepository: ClubReaderRepository
) {

    fun checkByEmail(email: String){
        if(userRepostiory.findByEmail(email) != null){
            throw SchoolIdAlreadyExistExcpetion.EXCPETION
        }
    }

    fun checkBySchoolId(schoolId: Int){
        if(userRepostiory.findBySchoolId(schoolId) != null){
            throw SchoolIdAlreadyExistExcpetion.EXCPETION
        }
    }

    fun isClubLeader(schoolId: Int): Boolean{
        if(clubReaderRepository.findBySchoolId(schoolId) != null){
            return true
        }else{
            return false
        }
    }





}