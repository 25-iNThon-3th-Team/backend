package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.UserUpdateRequest
import com.inthon.kourse.domain.model.UserView
import com.inthon.kourse.domain.service.UserService
import com.inthon.kourse.system.security.model.CustomUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun getCurrentUser(@AuthenticationPrincipal principal: CustomUserDetails): ResponseEntity<UserView> {
        val user = userService.getUserById(principal.getUser().id)
        return ResponseEntity.ok(user)
    }

    @PutMapping("/me")
    fun updateCurrentUser(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<UserView> {
        val user = userService.updateUser(principal.getUser().id, request)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserView> {
        return try {
            val user = userService.getUserById(id)
            ResponseEntity.ok(user)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/username/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<UserView> {
        return try {
            val user = userService.getUserByUsername(username)
            ResponseEntity.ok(user)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserView>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<UserView> {
        return try {
            val user = userService.updateUser(id, request)
            ResponseEntity.ok(user)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            userService.deleteUser(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}