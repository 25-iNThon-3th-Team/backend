package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import com.inthon.kourse.common.entity.PrimaryKeyEntity
import io.hypersistence.tsid.TSID
import jakarta.persistence.*

@Entity
@Table(name = "track")
data class Track(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id", nullable = false)
    val major: Major,

    @Column(nullable = false, length = 255)
    val name: String,

    @Column(columnDefinition = "TEXT")
    val description: String? = null
) : BaseEntity()