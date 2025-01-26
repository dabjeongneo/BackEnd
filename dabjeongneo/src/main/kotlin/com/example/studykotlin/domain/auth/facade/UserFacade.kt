package com.example.studykotlin.domain.auth.facade

import com.example.studykotlin.domain.ClubReader.repostiory.ClubReaderRepository
import com.example.studykotlin.domain.auth.excpetion.EmailAlreadyExistException
import com.example.studykotlin.domain.auth.excpetion.SchoolIdAlreadyExistExcpetion
import com.example.studykotlin.domain.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserFacade(
    val userRepository: UserRepository,
    val clubReaderRepository: ClubReaderRepository
) {

    fun checkByEmail(email: String){
        if(userRepository.findByEmail(email) != null){
            throw EmailAlreadyExistException.EXCPETION
        }
    }

    fun checkBySchoolId(schoolId: Int){
        if(userRepository.findBySchoolId(schoolId) != null){
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