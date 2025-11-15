package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.Course
import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.ClassView
import com.inthon.kourse.domain.model.CourseView
import com.inthon.kourse.domain.model.ScheduleSlot
import com.inthon.kourse.domain.model.TimetableSuggestionView
import com.inthon.kourse.domain.model.UserSchedulePreference
import com.inthon.kourse.domain.model.UserSchedulePreferenceRequest
import com.inthon.kourse.system.security.RestException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import kotlin.math.exp
import kotlin.random.Random

@Service
class TimetableGenerationService(
    private val timetableClassService: TimetableClassService,
    private val classService: ClassService,
    private val aiService: AIService,
){

    fun generateTimetable(request: UserSchedulePreferenceRequest, user: User): List<TimetableSuggestionView> {
        val preference = aiService.extractUserPreferences(request)
            ?: throw RestException(HttpStatus.UNPROCESSABLE_ENTITY, "AI Generation failed")

        val scoredClasses = candidateClass(user, preference)

        // Generate 10 diverse timetables using stochastic greedy
        return (1..10).map {
            generateSingleTimetable(scoredClasses, preference)
        }.sortedByDescending { it.totalScore }.map {
            TimetableSuggestionView(
                totalCredits = it.totalCredits,
                classes = it.classes,
                score = it.totalScore,
                note = preference.additionalNotes
            )
        }
    }

    private fun candidateClass(
        user: User,
        preference: UserSchedulePreference,
    ): List<ScoredClass> {
        val classes = classService.getAllClasses()
        val takenClasses = timetableClassService.getTookCourseFromTable(user.id).map { it.courseCode }

        val filteredClasses = classes.filter {
            val slot = it.schedule?.any{ scheduleSlot -> excludeSlot(preference, scheduleSlot) } ?: true
            val prof = it.professorName in preference.avoidProfessors
            val course = it.course.courseCode in preference.avoidCourses.map { it.courseCode }
            val taken = it.course.courseCode in takenClasses

            !slot && !prof && !course && !taken
        }

        val scoredClasses = filteredClasses.map { view ->
            var score = ((user.grade?.toInt() ?: 1) - (view.course.targetGrade?.toInt() ?: (view.course.courseCode[4]-'0'))).toDouble()
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

        return scoredClasses
    }

    /**
     * Stochastic Greedy Algorithm to generate a single timetable
     * Uses temperature-based probability selection to balance exploration and exploitation
     */
    private fun generateSingleTimetable(
        scoredClasses: List<ScoredClass>,
        preference: UserSchedulePreference,
        temperature: Double = 1.5  // Controls randomness: higher = more random, lower = more greedy
    ): TimetableCandidate {
        val selected = mutableListOf<ClassView>()
        var currentCredits = 0

        // Group by course to ensure we don't take the same course twice
        val availableClasses = scoredClasses.groupBy { it.classInfo.course.id }.toMutableMap()

        while (currentCredits < preference.maxCredits && availableClasses.isNotEmpty()) {
            // Get candidates that don't conflict and fit credit constraints
            val candidates = availableClasses.values
                .flatten()
                .filter { candidate ->
                    val newCredits = currentCredits + candidate.classInfo.course.credits
                    val fitsCredits = newCredits <= preference.maxCredits
                    val noConflict = !hasTimeConflict(selected, candidate.classInfo)
                    val satisfiesClassesPerDay = satisfiesMaxClassesPerDay(
                        selected + candidate.classInfo,
                        preference.maxClassesPerDay
                    )

                    fitsCredits && noConflict && satisfiesClassesPerDay
                }

            if (candidates.isEmpty()) break

            // Stochastic selection using softmax with temperature
            val selectedCandidate = stochasticSelect(candidates, temperature)

            selected.add(selectedCandidate.classInfo)
            currentCredits += selectedCandidate.classInfo.course.credits

            // Remove this course from available options (prevent taking same course twice)
            availableClasses.remove(selectedCandidate.classInfo.course.id)
        }

        // Calculate total score
        val totalScore = selected.sumOf { classView ->
            scoredClasses.find { it.classInfo.id == classView.id }?.score ?: 0.0
        }

        return TimetableCandidate(
            classes = selected,
            totalScore = totalScore,
            totalCredits = currentCredits
        )
    }

    /**
     * Stochastic selection using softmax probability distribution
     * Higher scored classes have higher probability, but not guaranteed
     */
    private fun stochasticSelect(
        candidates: List<ScoredClass>,
        temperature: Double
    ): ScoredClass {
        // Calculate softmax probabilities
        val maxScore = candidates.maxOf { it.score }
        val expScores = candidates.map { exp((it.score - maxScore) / temperature) }
        val sumExpScores = expScores.sum()
        val probabilities = expScores.map { it / sumExpScores }

        // Roulette wheel selection
        val random = Random.nextDouble()
        var cumulativeProbability = 0.0

        for (i in candidates.indices) {
            cumulativeProbability += probabilities[i]
            if (random <= cumulativeProbability) {
                return candidates[i]
            }
        }

        // Fallback (should not reach here)
        return candidates.last()
    }

    /**
     * Check if a class conflicts with any already selected classes
     */
    private fun hasTimeConflict(selected: List<ClassView>, candidate: ClassView): Boolean {
        val candidateSlots = candidate.schedule ?: return false

        for (selectedClass in selected) {
            val selectedSlots = selectedClass.schedule ?: continue

            for (candidateSlot in candidateSlots) {
                for (selectedSlot in selectedSlots) {
                    // Same day and overlapping time
                    if (candidateSlot.day == selectedSlot.day) {
                        val candidateStart = candidateSlot.startSlot
                        val candidateEnd = candidateSlot.endSlot
                        val selectedStart = selectedSlot.startSlot
                        val selectedEnd = selectedSlot.endSlot

                        // Check overlap
                        if (!(candidateEnd < selectedStart || candidateStart > selectedEnd)) {
                            return true
                        }
                    }
                }
            }
        }

        return false
    }

    /**
     * Check if adding classes violates max classes per day constraint
     */
    private fun satisfiesMaxClassesPerDay(classes: List<ClassView>, maxClassesPerDay: Int): Boolean {
        val classesByDay = mutableMapOf<String, Int>()

        for (classView in classes) {
            classView.schedule?.forEach { slot ->
                classesByDay[slot.day] = (classesByDay[slot.day] ?: 0) + 1
            }
        }

        return classesByDay.values.all { it <= maxClassesPerDay }
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