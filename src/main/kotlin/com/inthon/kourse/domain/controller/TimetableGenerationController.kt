package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.TimetableSuggestionView
import com.inthon.kourse.domain.model.UserSchedulePreferenceRequest
import com.inthon.kourse.domain.service.AIService
import com.inthon.kourse.domain.service.TimetableClassService
import com.inthon.kourse.domain.service.TimetableGenerationService
import com.inthon.kourse.system.security.model.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/timetables/generate")
class TimetableGenerationController(
    private val timetableGenerationService: TimetableGenerationService,
)
{
    @PostMapping
    fun generate(
        @AuthenticationPrincipal user: CustomUserDetails,
        @RequestBody request: UserSchedulePreferenceRequest
    ): ResponseEntity<List<TimetableSuggestionView>> {
        val result = timetableGenerationService.generateTimetable(request, user.getUser())
        return ResponseEntity.ok(result)
    }
}