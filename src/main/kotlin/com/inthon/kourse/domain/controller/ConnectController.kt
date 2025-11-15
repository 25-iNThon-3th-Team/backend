package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.SimpleUserView
import com.inthon.kourse.domain.service.ConnectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/connect")
class ConnectController(
    private val connectService: ConnectService,
) {
    @GetMapping
    fun connect() = connectService.fetchAll()
}