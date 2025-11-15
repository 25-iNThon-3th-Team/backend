package com.inthon.kourse.domain.service

import com.inthon.kourse.common.DomainMapper
import com.inthon.kourse.domain.model.ConnectView
import org.springframework.stereotype.Service

@Service
class ConnectService(
    private val timetableClassService: TimetableClassService,
    private val classService: ClassService,
    private val domainMapper: DomainMapper
){
    fun fetchAll(): List<ConnectView> {
        val list = timetableClassService.findAll()
            .groupBy { it.timetable?.user }
            .mapValues { it.value.mapNotNull { it.classEntity }}
        return list.mapNotNull { (user, courses) -> user?.let{
            ConnectView(
            domainMapper.toSimpleView(user),
                courses.map { classService.toClassView(it) }
            )}
         }
    }
}