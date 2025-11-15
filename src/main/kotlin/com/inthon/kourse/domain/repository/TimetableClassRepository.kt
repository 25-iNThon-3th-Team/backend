package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.TimetableClass
import com.inthon.kourse.domain.entity.TimetableClassId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimetableClassRepository : JpaRepository<TimetableClass, TimetableClassId> {
    fun findByTimetableId(timetableId: Long): List<TimetableClass>
    fun deleteByTimetableId(timetableId: Long)
}