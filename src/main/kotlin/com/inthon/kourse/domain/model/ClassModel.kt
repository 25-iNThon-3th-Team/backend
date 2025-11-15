package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

data class ScheduleSlot(
    val day: String,
    val start: String,
    val end: String,
    val location: String,
    val startSlot: Int,
    val endSlot: Int,
)

data class ClassView(
    @JsonSerialize(using= ToStringSerializer::class)
    val id: Long,
    val course: CourseView,
    val classCode: String,
    val professorName: String?,
    val schedule: List<ScheduleSlot>?,
    val totalSeats: Int
)

data class ClassCreateRequest(
    @JsonSerialize(using= ToStringSerializer::class)
    val courseId: Long,
    val classCode: String = "01",
    val professorName: String? = null,
    val schedule: List<ScheduleSlot>? = null,
    val totalSeats: Int = 50
)

data class ClassUpdateRequest(
    val classCode: String? = null,
    val professorName: String? = null,
    val schedule: List<ScheduleSlot>? = null,
    val totalSeats: Int? = null
)

data class ClassBulkCreateRequest(
    val classes: List<ClassCreateRequest>
)