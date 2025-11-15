package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.service.JinroService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping

class JinroController(
    private val jinroService: JinroService,
) {
    @GetMapping("/jinro")
    fun listJinro(
        @AuthenticationPrincipal user: UserDetails?,
    ){

    }
}