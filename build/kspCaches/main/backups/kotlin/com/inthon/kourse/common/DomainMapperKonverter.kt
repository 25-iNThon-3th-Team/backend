package com.inthon.kourse.common

import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.UserView
import io.mcarle.konvert.api.GeneratedKonverter
import org.springframework.stereotype.Component

@Component
public object DomainMapperImpl : DomainMapper {
  @GeneratedKonverter(priority = 5_000)
  override fun toView(user: User): UserView = UserView(
    id = user.id,
    username = user.username,
    enabled = user.enabled,
    roles = user.roles,
    grade = user.grade,
    semester = user.semester,
    majorCode = user.majorCode,
    creditsMajorRequired = user.creditsMajorRequired,
    creditsMajorElective = user.creditsMajorElective,
    creditsGeneral = user.creditsGeneral,
    preferredOffDays = user.preferredOffDays,
    preferredTimeSlot = user.preferredTimeSlot,
    maxTransferMinutes = user.maxTransferMinutes,
    priorityOrder = user.priorityOrder,
    createdAt = user.createdAt,
    lastModifiedAt = user.lastModifiedAt
  )
}
