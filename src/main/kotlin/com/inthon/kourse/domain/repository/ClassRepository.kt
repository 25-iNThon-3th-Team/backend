package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.Class
import com.inthon.kourse.domain.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassRepository : JpaRepository<Class, Long> {
    fun findByCourse(course: Course): List<Class>
    fun findByProfessorName(professorName: String): List<Class>
}