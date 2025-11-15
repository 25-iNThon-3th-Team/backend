package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.Timetable
import com.inthon.kourse.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimetableRepository : JpaRepository<Timetable, Long> {
    fun findByUser(user: User): List<Timetable>
    fun findByUserAndIsActive(user: User, isActive: Boolean): Timetable?
    fun findByUserAndGradeAndSemester(user: User, grade: Short, semester: String): List<Timetable>
}