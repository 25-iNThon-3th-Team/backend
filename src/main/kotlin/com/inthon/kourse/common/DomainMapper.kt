package com.inthon.kourse.common

import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.UserView
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface DomainMapper {
    fun toView(user: User): UserView
}