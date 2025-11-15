package com.inthon.kourse.domain.controller

import com.inthon.kourse.common.DomainMapper
import com.inthon.kourse.domain.model.LoginRequest
import com.inthon.kourse.system.security.model.CustomUserDetails
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolderStrategy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class LoginController(
    val authenticationManager: AuthenticationManager,
    val domainMapper: DomainMapper
) {

    private val securityContextRepository = HttpSessionSecurityContextRepository()
    private val securityContextHolderStrategy: SecurityContextHolderStrategy =
        SecurityContextHolder.getContextHolderStrategy()

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ) =
        try {
            val token = UsernamePasswordAuthenticationToken(request.username, request.password)
            val authentication = authenticationManager.authenticate(token)

            val context = SecurityContextHolder.getContext()
            context.authentication = authentication
            SecurityContextHolder.setContext(context)
            securityContextRepository.saveContext(context, httpServletRequest, httpServletResponse)

            ResponseEntity.ok(
                mapOf(
                    "message" to "Successfully logged in", "id" to
                            (authentication.principal as CustomUserDetails).getUser().id
                )
            )
        } catch (e: BadCredentialsException) {
            ResponseEntity.status(401).build<Any>()
        } catch (e: Exception) {
            ResponseEntity.status(500).build<Any>()
        }


    @PostMapping("/logout")
    fun logout() {

    }

}