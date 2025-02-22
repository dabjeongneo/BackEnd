package com.example.studykotlin.domain.auth.facade

import com.example.studykotlin.domain.club.repostiory.ClubRepository
import com.example.studykotlin.domain.auth.excpetion.EmailAlreadyExistException
import com.example.studykotlin.domain.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserFacade(
    val userRepository: UserRepository,
    val clubRepository: ClubRepository
) {

    fun checkByEmail(email: String){
        if(userRepository.findByEmail(email) != null){
            throw EmailAlreadyExistException.EXCPETION
        }
    }
}