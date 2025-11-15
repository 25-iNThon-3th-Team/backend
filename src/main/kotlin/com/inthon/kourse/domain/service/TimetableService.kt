package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.Timetable
import com.inthon.kourse.domain.entity.TimetableClass
import com.inthon.kourse.domain.model.*
import com.inthon.kourse.domain.repository.ClassRepository
import com.inthon.kourse.domain.repository.TimetableClassRepository
import com.inthon.kourse.domain.repository.TimetableRepository
import com.inthon.kourse.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.*

@Service
@Transactional(readOnly = true)
class TimetableService(
    private val timetableRepository: TimetableRepository,
    private val timetableClassRepository: TimetableClassRepository,
    private val userRepository: UserRepository,
    private val classRepository: ClassRepository,
    private val classService: ClassService
) {

    fun getTimetableById(id: Long): TimetableView {
        val timetable = timetableRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Timetable not found with id: $id")
        return toTimetableView(timetable)
    }

    fun getTimetablesByUserId(userId: Long): List<TimetableView> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException("User not found with id: $userId")
        return timetableRepository.findByUser(user).map { toTimetableView(it) }
    }

    fun getActiveTimetable(userId: Long): TimetableView? {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException("User not found with id: $userId")
        val timetable = timetableRepository.findByUserAndIsActive(user, true)
        return timetable?.let { toTimetableView(it) }
    }

    fun getTimetablesByUserAndSemester(userId: Long, year: String, semester: String): List<TimetableView> {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException("User not found with id: $userId")
        return timetableRepository.findByUserAndYearAndSemester(user, year, semester)
            .map { toTimetableView(it) }
    }

    @Transactional
    fun createTimetable(userId: Long, request: TimetableCreateRequest): TimetableView {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NoSuchElementException("User not found with id: $userId")

        // Calculate total credits
        val classes = request.classIds.mapNotNull { classRepository.findByIdOrNull(it) }
        val totalCredits = classes.sumOf { it.course.credits.toInt() }

        val timetable = Timetable(
            user = user,
            year = request.year,
            name = request.name,
            grade = request.grade,
            semester = request.semester,
            totalCredits = totalCredits,
            isActive = false,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now()
        )

        val savedTimetable = timetableRepository.save(timetable)

        // Add classes to timetable
        request.classIds.forEach { classId ->
            timetableClassRepository.save(
                TimetableClass(
                    timetableId = savedTimetable.id,
                    classId = classId
                )
            )
        }

        return toTimetableView(savedTimetable)
    }

    @Transactional
    fun updateTimetable(id: Long, request: TimetableUpdateRequest): TimetableView {
        val timetable = timetableRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Timetable not found with id: $id")

        // Handle class list update if provided
        var totalCredits = if (request.classIds != null) {
            // Remove all existing classes
            timetableClassRepository.deleteByTimetableId(id)

            // Add new classes
            val classes = request.classIds.mapNotNull { classRepository.findByIdOrNull(it) }
            request.classIds.forEach { classId ->
                timetableClassRepository.save(
                    TimetableClass(
                        timetableId = id,
                        classId = classId
                    )
                )
            }
            classes.sumOf { it.course.credits.toInt() }
        } else {
            timetable.totalCredits
        }

        timetable.apply {
            name = request.name ?: timetable.name
            grade = request.grade ?: timetable.grade
            semester = request.semester ?: timetable.semester
            this.totalCredits = totalCredits
            isActive = request.isActive ?: timetable.isActive
        }

        val saved = timetableRepository.save(timetable)
        return toTimetableView(saved)
    }

    @Transactional
    fun deleteTimetable(id: Long) {
        if (!timetableRepository.existsById(id)) {
            throw NoSuchElementException("Timetable not found with id: $id")
        }
        timetableClassRepository.deleteByTimetableId(id)
        timetableRepository.deleteById(id)
    }

    @Transactional
    fun addClassToTimetable(timetableId: Long, classId: Long): TimetableView {
        val timetable = timetableRepository.findByIdOrNull(timetableId)
            ?: throw NoSuchElementException("Timetable not found with id: $timetableId")

        val classEntity = classRepository.findByIdOrNull(classId)
            ?: throw NoSuchElementException("Class not found with id: $classId")

        // Check if class already exists in timetable
        val existingEntry = timetableClassRepository.findByIdOrNull(
            com.inthon.kourse.domain.entity.TimetableClassId(timetableId, classId)
        )
        if (existingEntry != null) {
            throw IllegalArgumentException("Class already exists in timetable")
        }

        // Add class to timetable
        timetableClassRepository.save(
            TimetableClass(
                timetableId = timetableId,
                classId = classId
            )
        )

        // Update total credits
        val updatedCredits = timetable.totalCredits + classEntity.course.credits.toInt()
        val updatedTimetable = Timetable(
            user = timetable.user,
            year = timetable.year,
            name = timetable.name,
            grade = timetable.grade,
            semester = timetable.semester,
            totalCredits = updatedCredits,
            isActive = timetable.isActive,
            createdAt = timetable.createdAt,
            updatedAt = OffsetDateTime.now()
        ).apply {
            (this as com.inthon.kourse.common.entity.PrimaryKeyEntity<Long>).javaClass
                .getDeclaredField("id").apply {
                    isAccessible = true
                    set(this@apply, timetableId)
                }
        }

        val saved = timetableRepository.save(updatedTimetable)
        return toTimetableView(saved)
    }

    @Transactional
    fun removeClassFromTimetable(timetableId: Long, classId: Long): TimetableView {
        val timetable = timetableRepository.findByIdOrNull(timetableId)
            ?: throw NoSuchElementException("Timetable not found with id: $timetableId")

        val classEntity = classRepository.findByIdOrNull(classId)
            ?: throw NoSuchElementException("Class not found with id: $classId")

        // Remove class from timetable
        timetableClassRepository.deleteById(
            com.inthon.kourse.domain.entity.TimetableClassId(timetableId, classId)
        )

        // Update total credits
        val updatedCredits = timetable.totalCredits - classEntity.course.credits.toInt()
        val updatedTimetable = Timetable(
            user = timetable.user,
            year = timetable.year,
            name = timetable.name,
            grade = timetable.grade,
            semester = timetable.semester,
            totalCredits = updatedCredits.coerceAtLeast(0),
            isActive = timetable.isActive,
            createdAt = timetable.createdAt,
            updatedAt = OffsetDateTime.now()
        ).apply {
            (this as com.inthon.kourse.common.entity.PrimaryKeyEntity<Long>).javaClass
                .getDeclaredField("id").apply {
                    isAccessible = true
                    set(this@apply, timetableId)
                }
        }

        val saved = timetableRepository.save(updatedTimetable)
        return toTimetableView(saved)
    }

    private fun toTimetableView(timetable: Timetable): TimetableView {
        val timetableClasses = timetableClassRepository.findByTimetableId(timetable.id)
        val classes = timetableClasses.mapNotNull { tc ->
            try {
                classService.getClassById(tc.classId)
            } catch (e: Exception) {
                null
            }
        }

        return TimetableView(
            id = timetable.id,
            userId = timetable.user.id.toString(),
            name = timetable.name,
            grade = timetable.grade,
            year = timetable.year,
            semester = timetable.semester,
            totalCredits = timetable.totalCredits,
            isActive = timetable.isActive,
            classes = classes,
            createdAt = timetable.createdAt,
            updatedAt = timetable.updatedAt
        )
    }
}