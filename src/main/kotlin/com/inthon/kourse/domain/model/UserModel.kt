package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDateTime
import java.util.UUID

data class UserView(
    @JsonSerialize(using= ToStringSerializer::class)
    val id: Long,
    val username: String,
    val enabled: Boolean,
    val roles: List<String>,
    val grade: Short?,
    val semester: Short?,
    val majorCode: String?,
    val creditsMajorRequired: Int,
    val creditsMajorElective: Int,
    val creditsGeneral: Int,
    val preferredOffDays: List<String>?,
    val preferredTimeSlot: String,
    val maxTransferMinutes: Short,
    val priorityOrder: List<String>?,
    val createdAt: LocalDateTime?,
    val lastModifiedAt: LocalDateTime?
)

data class SimpleUserView(
    val id: Long,
    val username: String,
)

data class UserCreateRequest(
    val username: String,
    val password: String,
    val grade: Short? = null,
    val semester: Short? = null,
    val majorCode: String? = null,
    val creditsMajorRequired: Int = 0,
    val creditsMajorElective: Int = 0,
    val creditsGeneral: Int = 0,
    val preferredOffDays: List<String>? = null,
    val preferredTimeSlot: String = "NONE",
    val maxTransferMinutes: Short = 20,
    val priorityOrder: List<String>? = null
)

data class UserUpdateRequest(
    val username: String? = null,
    val grade: Short? = null,
    val semester: Short? = null,
    val majorCode: String? = null,
    val creditsMajorRequired: Int? = null,
    val creditsMajorElective: Int? = null,
    val creditsGeneral: Int? = null,
    val preferredOffDays: List<String>? = null,
    val preferredTimeSlot: String? = null,
    val maxTransferMinutes: Short? = null,
    val priorityOrder: List<String>? = null
)