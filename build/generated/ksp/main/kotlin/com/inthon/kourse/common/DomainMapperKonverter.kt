package com.inthon.kourse.common

import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.UserView
import io.mcarle.konvert.api.GeneratedKonverter
import org.springframework.stereotype.Component

@Component
public object DomainMapperImpl : DomainMapper {
  @GeneratedKonverter(priority = 5_000)
  override fun toView(user: User): UserView = UserView(
    username = user.username,
    id = user.id
  )
}
