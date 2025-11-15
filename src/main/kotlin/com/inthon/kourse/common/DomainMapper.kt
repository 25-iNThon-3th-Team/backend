package com.inthon.kourse.common

import com.inthon.kourse.domain.entity.Course
import com.inthon.kourse.domain.entity.Major
import com.inthon.kourse.domain.entity.Track
import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.CourseView
import com.inthon.kourse.domain.model.MajorView
import com.inthon.kourse.domain.model.SimpleUserView
import com.inthon.kourse.domain.model.TrackView
import com.inthon.kourse.domain.model.UserView
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface DomainMapper {
    fun toView(user: User): UserView
    fun toSimpleView(user: User): SimpleUserView
    fun toView(course: Course): CourseView
    fun toView(major: Major): MajorView
}