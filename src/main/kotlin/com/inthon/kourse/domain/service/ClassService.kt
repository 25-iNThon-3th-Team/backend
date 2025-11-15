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

    private fun periodStrToSlot(periodStr: String): List<Int> {
        if (periodStr.indexOf('-') == -1) {
            return listOf(periodStr.toIntOrNull() ?: -1)
        } else {
            val split = periodStr.split('-')
            return listOf(split[0].toInt(), split[1].toInt())
        }
    }

    private fun periodStrToTime(periodStr: String): Pair<String, String> {
        if (periodStr.indexOf('-') == -1) {
            return periodToTime(periodStr.toIntOrNull() ?: -1)
        } else {
            val split = periodStr.split('-')
            return periodToTime(split[0].toInt()).first to periodToTime(split[1].toInt()).second
        }
    }

    private fun periodToTime(period: Int) =
        when (period) {
            0 -> "08:00" to "08:50"
            1 -> "09:00" to "10:15"
            2 -> "10:30" to "11:45"
            3 -> "12:00" to "13:15"
            4 -> "13:30" to "14:45"
            5 -> "15:00" to "16:15"
            6 -> "16:30" to "17:45"
            7 -> "18:00" to "18:50"
            8 -> "19:00" to "19:50"
            9 -> "20:00" to "20:50"
            10 -> "21:00" to "21:50"
            11 -> "22:00" to "22:50"
            else -> "00:00" to "00:00" // 유효하지 않은 값 처리
        }

    private fun toClassView(classEntity: Class): ClassView {
        val scheduleSlots = classEntity.schedule?.map { slot ->
            ScheduleSlot(
                day = slot["day"] ?: "",
                start = slot["start"] ?: periodStrToTime(slot["time_slot"] ?: "").first,
                end = slot["end"] ?: periodStrToTime(slot["time_slot"] ?: "").second,
                location = slot["location"] ?: "",
                startSlot = periodStrToSlot(slot["time_slot"] ?: "")[0],
                endSlot = periodStrToSlot(slot["time_slot"] ?: "").last(),
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