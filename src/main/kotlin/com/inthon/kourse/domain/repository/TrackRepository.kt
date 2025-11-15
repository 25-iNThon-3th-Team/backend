package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.Track
import org.springframework.data.jpa.repository.JpaRepository

interface TrackRepository : JpaRepository<Track, Long> {
}