package com.inthon.kourse.system.security

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

class RestException(
    statusCode: HttpStatus,
    message: String,
) : HttpStatusCodeException(statusCode, message) {
}