package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.model.UserSchedulePreferenceRequest
import com.inthon.kourse.system.security.RestException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TimetableGenerationService(
    private val courseService: CourseService,
    private val classService: ClassService,
    private val aiService: AIService,
){

    fun generateTimetable(request: UserSchedulePreferenceRequest) {
        val preference = aiService.extractUserPreferences(request)
            ?: throw RestException(HttpStatus.UNPROCESSABLE_ENTITY, "AI Generation failed")

    }


}