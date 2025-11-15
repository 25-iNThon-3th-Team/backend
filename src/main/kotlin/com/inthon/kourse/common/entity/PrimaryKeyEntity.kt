package com.inthon.kourse.common.entity

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.Objects

@MappedSuperclass
abstract class PrimaryKeyEntity<T : Serializable>(_id: T) : Persistable<T> {

    @Id
    @JvmField
    final val id: T = _id

    @Transient
    private var _isNew = true


    override fun getId(): T = id

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return compareIdentifier(other)
    }

    private fun compareIdentifier(obj: Any): Boolean {
        return if (obj is HibernateProxy) {
            id == (obj.hibernateLazyInitializer.identifier as? T)
        } else {
            id == (obj as? PrimaryKeyEntity<T>)?.id
        }
    }

    override fun hashCode() = Objects.hashCode(id)

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}