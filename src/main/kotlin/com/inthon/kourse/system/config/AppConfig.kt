package com.inthon.kourse.system.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableJpaAuditing
@Configuration
class AppConfig {
}