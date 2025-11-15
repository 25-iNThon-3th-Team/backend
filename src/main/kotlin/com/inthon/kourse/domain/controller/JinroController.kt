package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.TrackView
import com.inthon.kourse.domain.service.JinroService
import com.inthon.kourse.system.security.model.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/jinro")
class JinroController(
    private val jinroService: JinroService,
) {
    @GetMapping("")
    fun listJinro(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ResponseEntity<List<TrackView>> {
        val userid = user.getUser().id
        return ResponseEntity.ok(jinroService.getJinroPath(userid))
    }

    @GetMapping("/courses")
    fun getTakenCourses(
        @AuthenticationPrincipal user: CustomUserDetails,
    ) = ResponseEntity.ok(jinroService.getTakenCourse(user.getUser().id))

}