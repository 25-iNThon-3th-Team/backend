package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.CourseView
import com.inthon.kourse.domain.service.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping("/{id}")
    fun getCourseById(@PathVariable id: Long): ResponseEntity<CourseView> {
        return try {
            val course = courseService.getCourseById(id)
            ResponseEntity.ok(course)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/code/{courseCode}")
    fun getCourseByCourseCode(@PathVariable courseCode: String): ResponseEntity<CourseView> {
        return try {
            val course = courseService.getCourseByCourseCode(courseCode)
            ResponseEntity.ok(course)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllCourses(): ResponseEntity<List<CourseView>> {
        val courses = courseService.getAllCourses()
        return ResponseEntity.ok(courses)
    }

    @GetMapping("/major/{majorId}")
    fun getCoursesByMajorId(@PathVariable majorId: Long): ResponseEntity<List<CourseView>> {
        return try {
            val courses = courseService.getCoursesByMajorId(majorId)
            ResponseEntity.ok(courses)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/credit-type/{creditType}")
    fun getCoursesByCreditType(@PathVariable creditType: String): ResponseEntity<List<CourseView>> {
        val courses = courseService.getCoursesByCreditType(creditType)
        return ResponseEntity.ok(courses)
    }

    @GetMapping("/grade/{targetGrade}")
    fun getCoursesByTargetGrade(@PathVariable targetGrade: Short): ResponseEntity<List<CourseView>> {
        val courses = courseService.getCoursesByTargetGrade(targetGrade)
        return ResponseEntity.ok(courses)
    }
}