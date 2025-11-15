package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import com.inthon.kourse.common.entity.PrimaryKeyEntity
import io.hypersistence.tsid.TSID
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "course")
data class Course(
    @Column(name = "course_code", unique = true, nullable = false, length = 100)
    val courseCode: String,

    @Column(nullable = false, length = 255)
    val name: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    val major: Major? = null,

    @Column(name = "credit_type", nullable = false, length = 50)
    val creditType: String,

    @Column(nullable = false)
    val credits: Short = 3,

    @Column(name = "target_grade")
    val targetGrade: Short? = null,

    @Column(name = "competition_rate", precision = 5, scale = 2)
    val competitionRate: BigDecimal = BigDecimal("1.0"),

    @Column(name = "easiness_score", precision = 3, scale = 2)
    val easinessScore: BigDecimal = BigDecimal("3.0")
) : BaseEntity()