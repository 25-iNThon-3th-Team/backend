package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.Course
import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.ClassView
import com.inthon.kourse.domain.model.CourseView
import com.inthon.kourse.domain.model.ScheduleSlot
import com.inthon.kourse.domain.model.UserSchedulePreference
import com.inthon.kourse.domain.model.UserSchedulePreferenceRequest
import com.inthon.kourse.system.security.RestException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TimetableGenerationService(
    private val userService: UserService,
    private val courseService: CourseService,
    private val classService: ClassService,
    private val aiService: AIService,
){

    fun generateTimetable(request: UserSchedulePreferenceRequest) {
        val preference = aiService.extractUserPreferences(request)
            ?: throw RestException(HttpStatus.UNPROCESSABLE_ENTITY, "AI Generation failed")


    }

    private fun candidateClass(
        user: User,
        preference: UserSchedulePreference,
    ){
        val classes = classService.getAllClasses()

        val filteredClasses = classes.filter {
            val slot = it.schedule?.any{ slot -> excludeSlot(preference, slot) } ?: true
            val prof = it.professorName in preference.avoidProfessors
            val course = it.course.courseCode in preference.avoidCourses.map { it.courseCode }

            !slot && !prof && !course
        }

        val scoredClasses = filteredClasses.map { view ->
            var score = 1.0
            val weights = preference.scoringWeights

            if (view.schedule?.any { it.day in preference.preferredDays } == true) {
                score += 1.0 * weights.dayPreferenceWeight
            }

            if (view.schedule?.any { isTimeSlotIn(it.startSlot, it.endSlot, preference.preferredTimeSlots) } == true) {
                score += 1.0 * weights.timeSlotWeight
            }

            if (preference.preferEasyCourses) {
                score += (view.course.easinessScore.toDouble() / 5.0) * weights.easinessWeight
            }

            if (preference.preferLowCompetition) {
                // 경쟁률이 낮을수록(0에 가까울수록) 점수 높음
                score += (1.0 - (view.course.competitionRate.toDouble() / 5.0)) * weights.competitionRateWeight
            }

            if (view.professorName in preference.preferProfessors) {
                score += 1.0 * weights.professorPreferenceWeight
            }

            ScoredClass( view, score.coerceAtLeast(0.1))
        }

    }

    private fun excludeSlot(preference: UserSchedulePreference, slot: ScheduleSlot) =
        preference.avoidDays.contains(slot.day)
                || isTimeSlotIn(slot.startSlot, slot.endSlot, preference.preferredTimeSlots)

    private fun isTimeSlotIn(startSlot: Int, endSlot: Int, slotList: List<Int>) =
        startSlot in slotList || endSlot in slotList

}

data class ScoredClass(
    val classInfo: ClassView,
    val score: Double
)

data class TimetableCandidate(
    val classes: List<ClassView>, // (실제로는 Class DTO를 만들어 쓰는 것이 좋습니다)
    val totalScore: Double,
    val totalCredits: Int
)