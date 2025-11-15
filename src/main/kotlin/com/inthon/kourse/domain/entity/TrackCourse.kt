package com.inthon.kourse.domain.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "track_course")
@IdClass(TrackCourseId::class)
data class TrackCourse(
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "track_id", nullable = false)
    val track: Track,

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course
)

data class TrackCourseId(
    val track: Long = 0L,
    val course: Long = 0L
) : Serializable