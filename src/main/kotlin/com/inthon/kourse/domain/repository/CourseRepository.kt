package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.Course
import com.inthon.kourse.domain.entity.Major
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, Long> {
    fun findByCourseCode(courseCode: String): Course?
    fun findByMajor(major: Major): List<Course>
    fun findByCreditType(creditType: String): List<Course>
    fun findByTargetGrade(targetGrade: Short): List<Course>
    fun existsByCourseCode(courseCode: String): Boolean
}