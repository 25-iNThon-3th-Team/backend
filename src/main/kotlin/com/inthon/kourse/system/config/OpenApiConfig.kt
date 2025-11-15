package com.inthon.kourse.system.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
class OpenApiConfig {

    @Bean
    @Profile("dev")
    @Primary
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Kourse API")
                    .version("1.0.0")
                    .description("2025 iNThon 3th Team - Course Management API")
                    .contact(
                        Contact()
                            .name("iNThon Team")
                    )
            )
            .servers(
                listOf(
                    Server()
                        .url("http://localhost:8080")
                        .description("Local Development Server")
                )
            )
    }

    @Bean
    @Profile("deploy")
    fun customOpenAPIDeploy(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Kourse API")
                    .version("1.0.0")
                    .description("2025 iNThon 3th Team - Course Management API")
                    .contact(
                        Contact()
                            .name("iNThon Team")
                    )
            )
            .servers(
                listOf(
                    Server()
                        .url("http://inthon.fjey.me:8080")
                        .description("Remote Deployed Server")
                )
            )
    }
}