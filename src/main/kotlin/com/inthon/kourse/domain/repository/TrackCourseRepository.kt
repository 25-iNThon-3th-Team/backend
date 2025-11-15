package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.TrackCourse
import org.springframework.data.jpa.repository.JpaRepository

interface TrackCourseRepository: JpaRepository<TrackCourse, Long> {
}