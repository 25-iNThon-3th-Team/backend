package com.inthon.kourse.domain.model

data class ScheduleSlot(
    val day: String,
    val start: String,
    val end: String,
    val location: String
)

data class ClassView(
    val id: Long,
    val course: CourseView,
    val classCode: String,
    val professorName: String?,
    val schedule: List<ScheduleSlot>?,
    val totalSeats: Int
)

data class ClassCreateRequest(
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