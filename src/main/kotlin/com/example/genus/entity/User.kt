package com.example.genus.entity

import jakarta.persistence.*
import org.hibernate.annotations.GeneratedColumn
import org.hibernate.proxy.HibernateProxy
import java.util.UUID

@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var uuid: UUID = UUID.randomUUID(),

    @Column(unique = true)
    var email: String,

    @Column(unique = true)
    var username: String,

    var password: String

    ) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}