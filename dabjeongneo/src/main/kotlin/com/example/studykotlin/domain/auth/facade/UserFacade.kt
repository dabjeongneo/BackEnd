package com.example.studykotlin.domain.auth.facade

import com.example.studykotlin.domain.auth.excpetion.SchoolIdAlreadyExistExcpetion
import com.example.studykotlin.domain.user.domain.repository.UserRepostiory
import org.springframework.stereotype.Service

@Service
class UserFacade(
    val userRepostiory: UserRepostiory
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



}