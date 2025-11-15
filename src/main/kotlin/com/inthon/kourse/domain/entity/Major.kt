package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.PrimaryKeyEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "major")
data class Major(
    @Column(unique = true, nullable = false, length = 100)
    val code: String,

    @Column(nullable = false, length = 255)
    val name: String
) : PrimaryKeyEntity<Long>(0L)