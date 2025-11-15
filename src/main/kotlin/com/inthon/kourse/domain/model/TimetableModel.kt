package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.OffsetDateTime

data class TimetableView(
    @JsonSerialize(using= ToStringSerializer::class)
    val id: Long,
    val userId: String, // UUID as string
    val name: String,
    val year: String,
    val grade: Short,
    val semester: String,
    val totalCredits: Int,
    val isActive: Boolean,
    val classes: List<ClassView>,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)

data class TimetableSuggestionView(
    val totalCredits: Int,
    val classes: List<ClassView>,
    val score: Double
)

data class TimetableCreateRequest(
    val name: String,
    val year: String,
    val grade: Short,
    val semester: String,
    val classIds: List<Long> = emptyList()
)

data class TimetableUpdateRequest(
    val name: String? = null,
    val year: String? = null,
    val grade: Short? = null,
    val semester: String? = null,
    val isActive: Boolean? = null,
    val classIds: List<Long>? = null
)

data class TimetableAddClassRequest(
    val classId: Long
)

data class TimetableRemoveClassRequest(
    val classId: Long
)