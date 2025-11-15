package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.Course
import com.inthon.kourse.domain.model.CourseView
import com.inthon.kourse.domain.repository.CourseRepository
import com.inthon.kourse.domain.repository.MajorRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CourseService(
    private val courseRepository: CourseRepository,
    private val majorRepository: MajorRepository,
    private val majorService: MajorService
) {

    fun getCourseById(id: Long): CourseView {
        val course = courseRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Course not found with id: $id")
        return toCourseView(course)
    }

    fun getCourseByCourseCode(courseCode: String): CourseView {
        val course = courseRepository.findByCourseCode(courseCode)
            ?: throw NoSuchElementException("Course not found with code: $courseCode")
        return toCourseView(course)
    }

    fun getAllCourses(): List<CourseView> {
        return courseRepository.findAll().map { toCourseView(it) }
    }

    fun getCoursesByMajorId(majorId: Long): List<CourseView> {
        val major = majorRepository.findByIdOrNull(majorId)
            ?: throw NoSuchElementException("Major not found with id: $majorId")
        return courseRepository.findByMajor(major).map { toCourseView(it) }
    }

    fun getCoursesByCreditType(creditType: String): List<CourseView> {
        return courseRepository.findByCreditType(creditType).map { toCourseView(it) }
    }

    fun getCoursesByTargetGrade(targetGrade: Short): List<CourseView> {
        return courseRepository.findByTargetGrade(targetGrade).map { toCourseView(it) }
    }

    private fun toCourseView(course: Course): CourseView {
        return CourseView(
            id = course.id,
            courseCode = course.courseCode,
            name = course.name,
            major = course.major?.let { majorService.getMajorById(it.id) },
            creditType = course.creditType,
            credits = course.credits,
            targetGrade = course.targetGrade,
            competitionRate = course.competitionRate,
            easinessScore = course.easinessScore
        )
    }
}