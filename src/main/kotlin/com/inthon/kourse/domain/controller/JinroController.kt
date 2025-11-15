package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.TrackView
import com.inthon.kourse.domain.service.JinroService
import com.inthon.kourse.system.security.model.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JinroController(
    private val jinroService: JinroService,
) {
    @GetMapping("/jinro")
    fun listJinro(
        @AuthenticationPrincipal user: CustomUserDetails,
    ): ResponseEntity<List<TrackView>> {
        val userid = user.getUser().id
        return ResponseEntity.ok(jinroService.getJinroPath(userid))
    }
}