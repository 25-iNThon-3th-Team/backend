package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class CourseView(
    @JsonSerialize(using= ToStringSerializer::class)
    val id: Long,
    val courseCode: String,
    val name: String,
    val major: MajorView?,
    val creditType: String,
    val credits: Short,
    val targetGrade: Short?,
    val competitionRate: BigDecimal,
    val easinessScore: BigDecimal
)

data class CourseCreateRequest(
    val courseCode: String,
    val name: String,
    @JsonSerialize(using= ToStringSerializer::class)
    val majorId: Long?,
    val creditType: String,
    val credits: Short = 3,
    val targetGrade: Short? = null,
    val competitionRate: BigDecimal = BigDecimal("1.0"),
    val easinessScore: BigDecimal = BigDecimal("3.0")
)

data class CourseUpdateRequest(
    val name: String? = null,
    @JsonSerialize(using= ToStringSerializer::class)
    val majorId: Long? = null,
    val creditType: String? = null,
    val credits: Short? = null,
    val targetGrade: Short? = null,
    val competitionRate: BigDecimal? = null,
    val easinessScore: BigDecimal? = null
)

data class CourseBulkCreateRequest(
    val courses: List<CourseCreateRequest>
)