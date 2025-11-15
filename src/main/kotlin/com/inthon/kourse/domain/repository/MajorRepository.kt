package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.Major
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MajorRepository : JpaRepository<Major, Long> {
    fun findByCode(code: String): Major?
    fun existsByCode(code: String): Boolean
}