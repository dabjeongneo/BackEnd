package com.example.studykotlin.domain.user.domain


import com.example.studykotlin.domain.InterviewQuestion.domain.InterviewQuestion
import com.example.studykotlin.domain.user.domain.type.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String,
    private val password: String,
    val schoolId: Int,
    val role: Role ?= Role.USER,
    @OneToMany(mappedBy = "user") // mappedBy 쓴클래스가 조원(안쓴쪽이 주장) 조원은 조회밖에 못함.
    var interviewQuestions: MutableList<InterviewQuestion> = mutableListOf()
    ) : UserDetails{


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var authorities = mutableListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(role!!.key))
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
