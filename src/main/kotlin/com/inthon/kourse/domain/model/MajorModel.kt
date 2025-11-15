package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

data class MajorView(
    @JsonSerialize(using= ToStringSerializer::class)
    val id: Long,
    val code: String,
    val name: String
)

data class MajorCreateRequest(
    val code: String,
    val name: String
)

data class MajorUpdateRequest(
    val name: String
)