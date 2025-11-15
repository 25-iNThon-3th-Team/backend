package com.inthon.kourse

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KourseApplication

fun main(args: Array<String>) {
	runApplication<KourseApplication>(*args)
}
