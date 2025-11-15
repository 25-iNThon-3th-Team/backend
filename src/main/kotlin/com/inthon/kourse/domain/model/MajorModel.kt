package com.inthon.kourse.domain.model

data class MajorView(
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