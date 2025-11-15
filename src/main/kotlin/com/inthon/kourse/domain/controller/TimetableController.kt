package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.*
import com.inthon.kourse.domain.service.TimetableService
import com.inthon.kourse.system.security.model.CustomUserDetails
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/timetables")
class TimetableController(
    private val timetableService: TimetableService
) {

    @GetMapping("/{id}")
    fun getTimetableById(@PathVariable id: Long): ResponseEntity<TimetableView> {
        val timetable = timetableService.getTimetableById(id)
        return ResponseEntity.ok(timetable)
    }

    @GetMapping("/user/{userId}")
    fun getTimetablesByUserId(@PathVariable userId: Long): ResponseEntity<List<TimetableView>> {
        val timetables = timetableService.getTimetablesByUserId(userId)
        return ResponseEntity.ok(timetables)
    }

    @GetMapping("/me")
    fun getMyTimetables(@AuthenticationPrincipal principal: CustomUserDetails): ResponseEntity<List<TimetableView>> {
        val timetables = timetableService.getTimetablesByUserId(principal.getUser().id)
        return ResponseEntity.ok(timetables)
    }

    @GetMapping("/me/active")
    fun getMyActiveTimetable(@AuthenticationPrincipal principal: CustomUserDetails): ResponseEntity<TimetableView> {
        val timetable = timetableService.getActiveTimetable(principal.getUser().id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(timetable)
    }

    @GetMapping("/me/semester")
    fun getMyTimetablesBySemester(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @RequestParam year: String,
        @RequestParam semester: String
    ): ResponseEntity<List<TimetableView>> {
        val timetables = timetableService.getTimetablesByUserAndSemester(
            principal.getUser().id,
            year,
            semester
        )
        return ResponseEntity.ok(timetables)
    }

    @PostMapping
    fun createTimetable(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @RequestBody request: TimetableCreateRequest
    ): ResponseEntity<TimetableView> {
        val timetable = timetableService.createTimetable(principal.getUser().id, request)
        return ResponseEntity.status(HttpStatus.CREATED).body(timetable)
    }

    @PutMapping("/{id}")
    fun updateTimetable(
        @PathVariable id: Long,
        @RequestBody request: TimetableUpdateRequest
    ): ResponseEntity<TimetableView> {
        val timetable = timetableService.updateTimetable(id, request)
        return ResponseEntity.ok(timetable)
    }

    @DeleteMapping("/{id}")
    fun deleteTimetable(@PathVariable id: Long): ResponseEntity<Void> {
        timetableService.deleteTimetable(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/classes")
    fun addClassToTimetable(
        @PathVariable id: Long,
        @RequestBody request: TimetableAddClassRequest
    ): ResponseEntity<TimetableView> {
        val timetable = timetableService.addClassToTimetable(id, request.classId)
        return ResponseEntity.ok(timetable)
    }

    @DeleteMapping("/{id}/classes/{classId}")
    fun removeClassFromTimetable(
        @PathVariable id: Long,
        @PathVariable classId: Long
    ): ResponseEntity<TimetableView> {
        val timetable = timetableService.removeClassFromTimetable(id, classId)
        return ResponseEntity.ok(timetable)
    }
}