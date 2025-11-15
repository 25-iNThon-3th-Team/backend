package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.Class
import com.inthon.kourse.domain.entity.Course
import com.inthon.kourse.domain.entity.Timetable
import com.inthon.kourse.domain.entity.TimetableClass
import com.inthon.kourse.domain.entity.TimetableClassId
import com.inthon.kourse.domain.entity.User
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TimetableClassRepository : JpaRepository<TimetableClass, TimetableClassId>, KotlinJdslJpqlExecutor {
    fun findByTimetableId(timetableId: Long): List<TimetableClass>
    fun deleteByTimetableId(timetableId: Long)
}

fun TimetableClassRepository.findAllByUser(userId: Long): List<TimetableClass> =
    findAll {
        select(entity(TimetableClass::class))
            .from(entity(TimetableClass::class),
                innerJoin(TimetableClass::timetable))
            .where(path(Timetable::user)(User::id).eq(userId))
    }.filterNotNull()

fun TimetableClassRepository.findCoursesByUser(userId: Long): List<Course> =
    findAll {
        select(entity(Course::class))
            .from(entity(TimetableClass::class),
                innerJoin(TimetableClass::timetable),
                innerJoin(TimetableClass::classEntity),
                innerJoin(Class::course))
            .where(path(Timetable::user)(User::id).eq(userId))
    }.filterNotNull()