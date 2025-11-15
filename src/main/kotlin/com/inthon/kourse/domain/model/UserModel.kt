package com.inthon.kourse.domain.model

import java.util.UUID

data class UserView(
    val username: String,
    val id: UUID
)