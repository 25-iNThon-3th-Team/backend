package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.*
import com.inthon.kourse.domain.service.AIService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ai")
class AIController(
    private val aiService: AIService
) {

    @PostMapping("/chat")
    fun chat(@RequestBody request: ChatRequest): ResponseEntity<ChatResponse> {
        val response = aiService.chat(request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/chat/simple")
    fun chatSimple(@RequestBody request: Map<String, String>): ResponseEntity<Map<String, String>> {
        val message = request["message"] ?: return ResponseEntity.badRequest().build()
        val reply = aiService.chatSimple(message)
        return ResponseEntity.ok(mapOf("reply" to reply))
    }

    @PostMapping("/preferences/extract")
    fun extractPreferences(
        @RequestBody request: UserSchedulePreferenceRequest
    ): ResponseEntity<UserSchedulePreference> {
        val preferences = aiService.extractUserPreferences(request)
            ?: return ResponseEntity.internalServerError().build()
        return ResponseEntity.ok(preferences)
    }
}