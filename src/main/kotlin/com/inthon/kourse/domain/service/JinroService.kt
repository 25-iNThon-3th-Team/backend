package com.inthon.kourse.domain.service

import com.inthon.kourse.common.DomainMapper
import com.inthon.kourse.domain.entity.TrackCourse
import com.inthon.kourse.domain.model.CourseView
import com.inthon.kourse.domain.model.TrackView
import com.inthon.kourse.domain.repository.TrackCourseRepository
import org.springframework.stereotype.Service

@Service
class JinroService (
    val timetableClassService: TimetableClassService,
    val trackCourseRepository: TrackCourseRepository,
    val domainMapper: DomainMapper
){

    fun getJinroPath(userId: Long): List<TrackView> {
        val tracks = trackCourseRepository.findAll().groupBy {
            it.track
        }.mapValues { it.value.map { track -> track.course} }
        val courseTook = timetableClassService.getTookCourseFromTable(userId).map { it.id }
        val ratio = tracks.map { (track, courseList) ->
            var tookCount = 0
            for(c in courseList) {
                if(c.id in courseTook) {
                    tookCount++
                }
            }
            track to tookCount.toDouble() / courseList.size
        }.toMap()

        return tracks.map { (track, courseList) ->
            TrackView(
                track.id,
                track.name,
                track.description,
                ratio[track] ?: 0.0,
                courseList.map { domainMapper.toView(it) }
            )
        }

    }

    fun getTakenCourse(userId: Long) =
        timetableClassService.getTookCourseFromTable(userId).map { domainMapper.toView(it) }

    fun getTakenClass(userId: Long) =
        timetableClassService.
}