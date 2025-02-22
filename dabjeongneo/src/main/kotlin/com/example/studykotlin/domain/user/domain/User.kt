package com.example.studykotlin.domain.user.domain

import com.example.studykotlin.domain.user.domain.type.Role
import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    val id: UUID? = null,

    @Column(columnDefinition = "VARCHAR(60)")
    val email: String,

    @Column(columnDefinition = "VARCHAR(60)")
    private val password: String,

    @Column(columnDefinition = "DATETIME(6)")
    private val create_at: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    val role: Role

    ) : UserDetails {

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
