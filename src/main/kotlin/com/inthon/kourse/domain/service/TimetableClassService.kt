package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.repository.TimetableClassRepository
import com.inthon.kourse.domain.repository.findAllByUser
import com.inthon.kourse.domain.repository.findCoursesByUser
import org.springframework.stereotype.Service

@Service
class TimetableClassService(
    private val timetableClassRepository: TimetableClassRepository,
    private val userService: UserService,
) {

    fun getTimetableClassByUserId(userId: Long) = timetableClassRepository.findAllByUser(userId)

    fun getTookCourseFromTable(userId: Long) = timetableClassRepository.findCoursesByUser(userId)
}