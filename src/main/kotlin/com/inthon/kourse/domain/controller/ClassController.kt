package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.ClassView
import com.inthon.kourse.domain.service.ClassService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/classes")
class ClassController(
    private val classService: ClassService
) {

    @GetMapping("/{id}")
    fun getClassById(@PathVariable id: Long): ResponseEntity<ClassView> {
        return try {
            val classEntity = classService.getClassById(id)
            ResponseEntity.ok(classEntity)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getAllClasses(): ResponseEntity<List<ClassView>> {
        val classes = classService.getAllClasses()
        return ResponseEntity.ok(classes)
    }

    @GetMapping("/course/{courseId}")
    fun getClassesByCourseId(@PathVariable courseId: Long): ResponseEntity<List<ClassView>> {
        return try {
            val classes = classService.getClassesByCourseId(courseId)
            ResponseEntity.ok(classes)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/professor/{professorName}")
    fun getClassesByProfessor(@PathVariable professorName: String): ResponseEntity<List<ClassView>> {
        val classes = classService.getClassesByProfessor(professorName)
        return ResponseEntity.ok(classes)
    }
}