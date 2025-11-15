package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.UserCreateRequest
import com.inthon.kourse.domain.model.UserView
import com.inthon.kourse.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class RegisterController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: UserCreateRequest): ResponseEntity<UserView> {
        return try {
            val user = userService.createUser(request)
            ResponseEntity.status(HttpStatus.CREATED).body(user)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }
}