package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import com.inthon.kourse.common.entity.PrimaryKeyEntity
import io.hypersistence.tsid.TSID
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(
    name = "timetable",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["user_id", "name", "grade", "semester"])
    ]
)
data class Timetable(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(nullable = false, length = 255)
    var name: String,

    @Column(nullable = false)
    var grade: Short,

    @Column(nullable = false)
    var semester: Short,

    @Column(name = "total_credits", nullable = false)
    var totalCredits: Int = 0,

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = false,

    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()
) : BaseEntity()