package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.model.ChatMessage
import com.inthon.kourse.domain.model.ChatRequest
import com.inthon.kourse.domain.model.ChatResponse
import com.inthon.kourse.domain.model.ClassView
import com.inthon.kourse.domain.model.CourseView
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.stereotype.Service

@Service
class AIService(
    private val chatClientBuilder: ChatClient.Builder,
    private val courseService: CourseService,
    private val classService: ClassService
) {

    private val systemPrompt = """
        You are Kourse AI, an intelligent course recommendation assistant for university students.

        Your role is to help students with:
        1. Course selection and planning based on their major, grade level, and interests
        2. Understanding graduation requirements (전공필수, 전공선택, 교양)
        3. Suggesting courses based on competition rates and difficulty
        4. Creating optimal course schedules considering time constraints
        5. Recommending academic tracks (e.g., AI track, Data Science track)

        Guidelines:
        - Always be helpful, friendly, and encouraging
        - Provide clear, concise explanations in Korean or English based on user preference
        - When discussing courses, mention relevant factors like credits, competition rate, and difficulty
        - Consider the student's preferences like preferred days off and time slots
        - Prioritize graduation requirements when making recommendations

        Be conversational and supportive while maintaining professionalism.
    """.trimIndent()

    private val chatClient = chatClientBuilder
        .defaultSystem(systemPrompt)
        .build()

    fun chat(request: ChatRequest): ChatResponse {
        // Build conversation history
        val messages = mutableListOf<org.springframework.ai.chat.messages.Message>()

        // Add conversation history if provided
        request.conversationHistory?.forEach { msg ->
            when (msg.role.lowercase()) {
                "user" -> messages.add(UserMessage(msg.content))
                "assistant" -> messages.add(AssistantMessage(msg.content))
            }
        }

        // Build context with relevant courses
        val courseContext = buildCourseContext(request.message)

        // Create enhanced user message with context
        val enhancedMessage = if (courseContext.isNotEmpty()) {
            """
            User Query: ${request.message}

            Available Course Information:
            $courseContext

            Please answer the user's query using the course information provided above.
            """.trimIndent()
        } else {
            request.message
        }

        // Add current user message with context
        messages.add(UserMessage(enhancedMessage))

        // Get response from AI
        val response = chatClient
            .prompt()
            .messages(messages)
            .call()
            .content()

        // Build updated conversation history
        val updatedHistory = (request.conversationHistory ?: emptyList()).toMutableList()
        updatedHistory.add(ChatMessage("user", request.message))
        updatedHistory.add(ChatMessage("assistant", response ?: ""))

        return ChatResponse(
            reply = response ?: "Sorry, I couldn't generate a response.",
            conversationHistory = updatedHistory
        )
    }

    private fun buildCourseContext(userMessage: String): String {
        val allCourses = courseService.getAllCourses()

        // Filter courses based on user query keywords
        val filteredCourses = filterRelevantCourses(allCourses, userMessage)

        // If filtering didn't help much, still limit to prevent token overflow
        val coursesToInclude = if (filteredCourses.size > 50) {
            filteredCourses.take(50)
        } else {
            filteredCourses
        }

        val courseInfoList = coursesToInclude.map { course ->
            // Get class schedule information for this course
            val classes = try {
                classService.getClassesByCourseId(course.id)
            } catch (e: Exception) {
                emptyList()
            }

            buildSimpleCourseInfo(course, classes)
        }

        return courseInfoList.joinToString("\n")
    }

    private fun filterRelevantCourses(
        courses: List<CourseView>,
        userMessage: String
    ): List<CourseView> {
        val messageLower = userMessage.lowercase()

        // Extract keywords from user message
        val keywords = extractKeywords(messageLower)

        // If no specific keywords, return all courses (will be limited later)
        if (keywords.isEmpty()) {
            return courses
        }

        // Score and sort courses by relevance
        return courses
            .map { course -> course to calculateRelevanceScore(course, keywords, messageLower) }
            .filter { it.second > 0 } // Only include courses with some relevance
            .sortedByDescending { it.second }
            .map { it.first }
            .ifEmpty { courses } // If no matches, return all
    }

    private fun extractKeywords(message: String): Map<String, Any> {
        val keywords = mutableMapOf<String, Any>()

        // Credit types
        when {
            message.contains("전공필수") || message.contains("major required") ->
                keywords["creditType"] = "MAJOR_REQUIRED"
            message.contains("전공선택") || message.contains("major elective") ->
                keywords["creditType"] = "MAJOR_ELECTIVE"
            message.contains("교양") || message.contains("general") ->
                keywords["creditType"] = "GENERAL"
        }

        // Days of week
        val days = listOf("월", "화", "수", "목", "금", "토", "일", "mon", "tue", "wed", "thu", "fri", "sat", "sun")
        keywords["days"] = days.filter { message.contains(it) }

        // Time preferences
        when {
            message.contains("오전") || message.contains("morning") -> keywords["timeSlot"] = "AM"
            message.contains("오후") || message.contains("afternoon") -> keywords["timeSlot"] = "PM"
        }

        // Grade level
        val gradeMatch = Regex("(\\d)학년").find(message)
        gradeMatch?.groupValues?.get(1)?.toShortOrNull()?.let { keywords["grade"] = it }

        // Difficulty preferences
        when {
            message.contains("쉬운") || message.contains("easy") -> keywords["easyPreference"] = true
            message.contains("어려운") || message.contains("hard") || message.contains("challenging") ->
                keywords["hardPreference"] = true
        }

        // Competition rate preferences
        when {
            message.contains("경쟁률 낮은") || message.contains("low competition") ->
                keywords["lowCompetition"] = true
            message.contains("경쟁률 높은") || message.contains("high competition") ->
                keywords["highCompetition"] = true
        }

        return keywords
    }

    private fun calculateRelevanceScore(
        course: CourseView,
        keywords: Map<String, Any>,
        messageLower: String
    ): Int {
        var score = 0

        // Match credit type (high priority)
        keywords["creditType"]?.let { type ->
            if (course.creditType == type) score += 10
        }

        // Match grade level
        keywords["grade"]?.let { grade ->
            if (course.targetGrade == grade) score += 5
        }

        // Match course name or code in message
        if (messageLower.contains(course.name.lowercase()) ||
            messageLower.contains(course.courseCode.lowercase())) {
            score += 15
        }

        // Match major name
        course.major?.name?.let { majorName ->
            if (messageLower.contains(majorName.lowercase())) score += 8
        }

        // Difficulty preferences
        keywords["easyPreference"]?.let {
            if (course.easinessScore.toDouble() <= 3.0) score += 7
        }
        keywords["hardPreference"]?.let {
            if (course.easinessScore.toDouble() >= 4.0) score += 7
        }

        // Competition rate preferences
        keywords["lowCompetition"]?.let {
            if (course.competitionRate.toDouble() <= 1.5) score += 7
        }
        keywords["highCompetition"]?.let {
            if (course.competitionRate.toDouble() >= 2.0) score += 7
        }

        // Day/time matching requires checking class schedules
        val requestedDays = keywords["days"] as? List<*>
        if (requestedDays?.isNotEmpty() == true) {
            try {
                val classes = classService.getClassesByCourseId(course.id)
                classes.forEach { classInfo ->
                    classInfo.schedule?.forEach { slot ->
                        val dayLower = slot.day.lowercase()
                        if (requestedDays.any { dayLower.startsWith(it.toString()) }) {
                            score += 6
                        }
                    }
                }
            } catch (e: Exception) {
                // Ignore schedule checking errors
            }
        }

        return score
    }

    private fun buildSimpleCourseInfo(
        course: CourseView,
        classes: List<ClassView>
    ): String {
        val scheduleInfo = if (classes.isNotEmpty()) {
            classes.joinToString(" | ") { classInfo ->
                val schedule = classInfo.schedule?.joinToString(", ") { slot ->
                    "${slot.day} ${slot.start}-${slot.end}"
                } ?: "TBA"
                "Class ${classInfo.classCode}: $schedule (${classInfo.professorName ?: "TBA"})"
            }
        } else {
            "No schedule available"
        }

        return "${course.name} [${course.courseCode}] - ${course.credits}학점, ${course.creditType}, 경쟁률: ${course.competitionRate}, 난이도: ${course.easinessScore} | $scheduleInfo"
    }

    fun chatSimple(message: String): String {
        return chatClient
            .prompt()
            .user(message)
            .call()
            .content() ?: "Sorry, I couldn't generate a response."
    }

    fun extractUserPreferences(
        request: com.inthon.kourse.domain.model.UserSchedulePreferenceRequest
    ): com.inthon.kourse.domain.model.UserSchedulePreference? {
        // Get all available courses for reference
        val allCourses = courseService.getAllCourses()
        val courseReference = buildCourseReferenceList(allCourses)

        val extractionPrompt = buildPreferenceExtractionPrompt(request.plainTextInput, courseReference)

        // Use structured output to extract preferences
        val preferences = chatClient
            .prompt()
            .user(extractionPrompt)
            .call()
            .entity(com.inthon.kourse.domain.model.UserSchedulePreference::class.java)

        return preferences
    }

    private fun buildCourseReferenceList(courses: List<CourseView>): String {
        // Build a concise reference list of all available courses
        return courses.joinToString("\n") { course ->
            "${course.courseCode} -> ${course.name} (${course.credits}학점, ${course.creditType})"
        }
    }

    private fun buildPreferenceExtractionPrompt(plainText: String, courseReference: String): String {
        return """
            Extract detailed scheduling preferences from the following user input:

            "$plainText"

            **Available Courses Reference:**
            $courseReference

            Parse this text and extract structured preferences for course scheduling. Consider:

            1. **Credit Requirements:**
               - Target number of credits (default: 15)
               - Minimum and maximum acceptable credits

            2. **Day Preferences:**
               - Preferred days of the week (MON, TUE, WED, THU, FRI, SAT, SUN)
               - Days to avoid
               - Maximum classes per day

            3. **Time Preferences:**
               - Preferred time slots: MORNING (before 12:00), AFTERNOON (12:00-18:00), EVENING (after 18:00)
               - Time slots to avoid
               - Whether user prefers consecutive classes (back-to-back vs spread out)

            4. **Course Type Priorities:**
               - Prioritize major required courses (전공필수)?
               - Prefer courses with low competition rates?
               - Prefer easier courses (higher easiness score)?

            5. **Professor Preferences:**
               - Specific professors to prefer or avoid

            6. **Course Constraints:**
               - Required courses (must take) - IMPORTANT: Resolve course codes/abbreviations to full course information
               - Courses to avoid - IMPORTANT: Resolve course codes to full course information
               When extracting course requirements, you MUST follow these strict rules:
               - Rule 1: High-Confidence Matches ONLY. 
                 * Your primary goal is to avoid false positives (incorrect matches). If a user's input is ambiguous, partial, or not a 100% certain match with the reference, YOU MUST IGNORE IT. 
               It is better to miss a potential match than to include an incorrect one.
               - Rule 2: Match Priority. You must only match based on:
                 * Exact Course Code: (e.g., "COSE201"). This is the most reliable signal.
                 * Full, Exact Course Name: (e.g., "자료구조"). The user must type the full name as it appears in the reference.
               - Rule 3: What to AVOID.
                 * DO NOT match on partial names (e.g., if the user says "자료", DO NOT guess "자료구조").
                 * DO NOT match on general abbreviations or nicknames (e.g., "컴프", "알고", "운체").
               For each high-confidence match (Rule 2), create a ResolvedCourse with:
               - Match course codes, abbreviations, or partial names from user input against the Available Courses Reference
               - For each course mentioned, create a ResolvedCourse with:
                 * courseCode: The official course code from the reference
                 * courseName: The full course name from the reference
                 * courseId: null (will be resolved server-side)
                 
               Examples:
               - "I must take COSE201" → Match: COSE201. (Reason: Exact Code Match)
               - "꼭 들어야 하는 게 자료구조" → Match: 자료구조. (Reason: Full Name Match)
               - "컴프 수업은 피하고 싶어" → NO MATCH. (Reason: "컴프" is a partial name and ambiguous.)
               - "COSE21" → NO MATCH. (Reason: COSE21 is a partial code.)

            7. **Scoring Weights:**
               - Relative importance of day preference (0.0-2.0)
               - Relative importance of time slot (0.0-2.0)
               - Relative importance of competition rate (0.0-2.0)
               - Relative importance of easiness (0.0-2.0)
               - Relative importance of graduation requirements (0.0-2.0)
               - Relative importance of professor preference (0.0-2.0)

            8. **Additional Notes:**
               - Any other constraints or preferences mentioned

            If any preference is not mentioned, use reasonable defaults:
            - targetCredits: 15
            - minCredits: 12
            - maxCredits: 18
            - maxClassesPerDay: 4
            - prioritizeMajorRequired: true
            - All weights: 1.0 (except graduationRequirementWeight: 2.0)

            Parse the text carefully and extract all mentioned preferences. If user says things like:
            - "월요일은 쉬고 싶어요" → add MON to avoidDays
            - "아침 일찍 수업 싫어" → add MORNING to avoidTimeSlots
            - "경쟁률 낮은 과목" → set preferLowCompetition to true
            - "쉬운 과목" → set preferEasyCourses to true
            - "16학점" → set targetCredits to 16
            - "연강 괜찮아요" → set preferConsecutiveClasses to true
        """.trimIndent()
    }
}