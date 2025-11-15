package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

data class TrackView(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val name: String,
    val description: String?,
    val percentage: Double,
    val courses: List<CourseView>
)