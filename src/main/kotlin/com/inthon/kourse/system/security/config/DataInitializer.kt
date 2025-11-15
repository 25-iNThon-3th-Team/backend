package com.inthon.kourse.system.security.config

import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class DataInitializer {

    @Bean
    fun initializeData(
        userRepository: UserRepository,
        passwordEncoder: BCryptPasswordEncoder
    ): CommandLineRunner {
        return CommandLineRunner {
            // Create sample users if the database is empty
            if (userRepository.count() == 0L) {
                // Admin user
                userRepository.save(
                    User(
                        userId = "admin",
                        username = "admin",
                        password = passwordEncoder.encode("admin123"),
                        enabled = true,
                        roles = listOf("ROLE_USER", "ROLE_ADMIN")
                    )
                )

                // Regular user
                userRepository.save(
                    User(
                        userId = "user",
                        username = "user",
                        password = passwordEncoder.encode("user123"),
                        enabled = true,
                        roles = listOf("ROLE_USER")
                    )
                )

                println("Sample users created:")
                println("- admin/admin123 (ADMIN)")
                println("- user/user123 (USER)")
            }
        }
    }
}