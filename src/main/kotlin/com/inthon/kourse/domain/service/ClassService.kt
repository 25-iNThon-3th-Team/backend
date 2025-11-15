package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.Class
import com.inthon.kourse.domain.model.ClassView
import com.inthon.kourse.domain.model.ScheduleSlot
import com.inthon.kourse.domain.repository.ClassRepository
import com.inthon.kourse.domain.repository.CourseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ClassService(
    private val classRepository: ClassRepository,
    private val courseRepository: CourseRepository,
    private val courseService: CourseService
) {

    fun getClassById(id: Long): ClassView {
        val classEntity = classRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Class not found with id: $id")
        return toClassView(classEntity)
    }

    fun getAllClasses(): List<ClassView> {
        return classRepository.findAll().map { toClassView(it) }
    }

    fun getClassesByCourseId(courseId: Long): List<ClassView> {
        val course = courseRepository.findByIdOrNull(courseId)
            ?: throw NoSuchElementException("Course not found with id: $courseId")
        return classRepository.findByCourse(course).map { toClassView(it) }
    }

    fun getClassesByProfessor(professorName: String): List<ClassView> {
        return classRepository.findByProfessorName(professorName).map { toClassView(it) }
    }

    private fun toClassView(classEntity: Class): ClassView {
        val scheduleSlots = classEntity.schedule?.map { slot ->
            ScheduleSlot(
                day = slot["day"] ?: "",
                start = slot["start"] ?: "",
                end = slot["end"] ?: "",
                location = slot["location"] ?: ""
            )
        }

        return ClassView(
            id = classEntity.id,
            course = courseService.getCourseById(classEntity.course.id),
            classCode = classEntity.classCode,
            professorName = classEntity.professorName,
            schedule = scheduleSlots,
            totalSeats = classEntity.totalSeats
        )
    }
}