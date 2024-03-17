package com.example.genus.entity

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import java.net.URL

@Entity
@Table(name = "videos")
class Video (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Column(nullable = false)
    var url: URL,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "video_tag", joinColumns = [JoinColumn(name = "video_id")], inverseJoinColumns = [JoinColumn(name = "tag_id")])
    var tags: MutableSet<Tag> = mutableSetOf(),

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category

    ) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Video

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , url = $url )"
    }

}