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
            val users = userRepository.findAll()
            users.forEach {
                it.password = passwordEncoder.encode("admin123")
                userRepository.save(it)
            }
        }
    }
}