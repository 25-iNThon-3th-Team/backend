package com.inthon.kourse.domain.model

data class UserSchedulePreferenceRequest(
    val plainTextInput: String
)

data class UserSchedulePreference(
    val targetCredits: Int = 15,
    val minCredits: Int = 12,
    val maxCredits: Int = 18,
    val preferredDays: List<String> = emptyList(),
    val avoidDays: List<String> = emptyList(),
    val preferredTimeSlots: List<Int> = emptyList(), // e.g., ["MORNING", "AFTERNOON", "EVENING"]
    val avoidTimeSlots: List<Int> = emptyList(),
    val maxClassesPerDay: Int = 4,
    val preferConsecutiveClasses: Boolean = false,
    val prioritizeMajorRequired: Boolean = true,
    val preferLowCompetition: Boolean = false,
    val preferEasyCourses: Boolean = false,
    val avoidProfessors: List<String> = emptyList(),
    val preferProfessors: List<String> = emptyList(),
    val requiredCourses: List<ResolvedCourse> = emptyList(), // Resolved course requirements
    val avoidCourses: List<ResolvedCourse> = emptyList(), // Courses to avoid
    val scoringWeights: ScoringWeights = ScoringWeights(),
    val additionalNotes: String = ""
)

data class ResolvedCourse(
    val courseCode: String,
    val courseName: String,
    val courseId: Long? = null
)

data class ScoringWeights(
    val dayPreferenceWeight: Double = 1.0,
    val timeSlotWeight: Double = 1.0,
    val competitionRateWeight: Double = 0.5,
    val easinessWeight: Double = 0.5,
    val graduationRequirementWeight: Double = 2.0,
    val professorPreferenceWeight: Double = 0.3
)