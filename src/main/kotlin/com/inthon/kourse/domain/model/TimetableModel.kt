package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.OffsetDateTime

data class TimetableView(
    @JsonSerialize(using= ToStringSerializer::class)
    val id: Long,
    val userId: String, // UUID as string
    val name: String,
    val grade: Short,
    val semester: Short,
    val totalCredits: Int,
    val isActive: Boolean,
    val classes: List<ClassView>,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)

data class TimetableCreateRequest(
    val name: String,
    val grade: Short,
    val semester: Short,
    val classIds: List<Long> = emptyList()
)

data class TimetableUpdateRequest(
    val name: String? = null,
    val grade: Short? = null,
    val semester: Short? = null,
    val isActive: Boolean? = null,
    val classIds: List<Long>? = null
)

data class TimetableAddClassRequest(
    val classId: Long
)

data class TimetableRemoveClassRequest(
    val classId: Long
)