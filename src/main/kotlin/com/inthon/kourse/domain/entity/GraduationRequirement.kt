package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import com.inthon.kourse.common.entity.PrimaryKeyEntity
import io.hypersistence.tsid.TSID
import jakarta.persistence.*

@Entity
@Table(
    name = "graduation_requirement",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["major_id", "admission_year", "credit_type"])
    ]
)
data class GraduationRequirement(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id", nullable = false)
    val major: Major,

    @Column(name = "admission_year", nullable = false)
    val admissionYear: Int,

    @Column(name = "credit_type", nullable = false, length = 50)
    val creditType: String,

    @Column(name = "required_credits", nullable = false)
    val requiredCredits: Int
) : BaseEntity()