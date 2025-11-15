package com.inthon.kourse.domain.model

data class ConnectView(
    val user: SimpleUserView,
    val courses: List<ClassView>,
)