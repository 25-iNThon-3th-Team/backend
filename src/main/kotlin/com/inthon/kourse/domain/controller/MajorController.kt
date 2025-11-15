package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.MajorView
import com.inthon.kourse.domain.service.MajorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/majors")
class MajorController(
    private val majorService: MajorService
) {

    @GetMapping("/{id}")
    fun getMajorById(@PathVariable id: Long): ResponseEntity<MajorView> {
        return try {
            val major = majorService.getMajorById(id)
            ResponseEntity.ok(major)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/code/{code}")
    fun getMajorByCode(@PathVariable code: String): ResponseEntity<MajorView> {
        return try {
            val major = majorService.getMajorByCode(code)
            ResponseEntity.ok(major)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllMajors(): ResponseEntity<List<MajorView>> {
        val majors = majorService.getAllMajors()
        return ResponseEntity.ok(majors)
    }
}