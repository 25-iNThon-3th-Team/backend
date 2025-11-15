package com.inthon.kourse.system.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            securityContext {
                securityContextRepository = HttpSessionSecurityContextRepository()
            }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.IF_REQUIRED }
            authorizeHttpRequests {
                authorize("/login", permitAll)
                authorize("/register", permitAll)
                authorize("/logout", permitAll)
                authorize("/h2-console/**", permitAll)
                authorize("/swagger-ui/**", permitAll)
                authorize("/swagger-ui.html", permitAll)
                authorize("/v3/api-docs/**", permitAll)
                authorize("/api-docs/**", permitAll)
                authorize("/api/ai/**", permitAll)
                // TODO Authenticate not allowed endpoints
                authorize(anyRequest, permitAll)
            }
            formLogin { disable() }
            httpBasic { disable() }
            logout {
                logoutUrl = "/logout"
                logoutSuccessHandler = HttpStatusReturningLogoutSuccessHandler()
            }

        }
        return http.build()
    }

    @Bean
    fun authenticationManager(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)

        return ProviderManager(authenticationProvider)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("http://localhost:5173")
        corsConfiguration.allowedMethods = listOf("*")
        corsConfiguration.allowedHeaders = listOf("*")
        corsConfiguration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}