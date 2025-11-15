package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.Major
import com.inthon.kourse.domain.model.MajorView
import com.inthon.kourse.domain.repository.MajorRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MajorService(
    private val majorRepository: MajorRepository
) {

    fun getMajorById(id: Long): MajorView {
        val major = majorRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("Major not found with id: $id")
        return toMajorView(major)
    }

    fun getMajorByCode(code: String): MajorView {
        val major = majorRepository.findByCode(code)
            ?: throw NoSuchElementException("Major not found with code: $code")
        return toMajorView(major)
    }

    fun getAllMajors(): List<MajorView> {
        return majorRepository.findAll().map { toMajorView(it) }
    }

    private fun toMajorView(major: Major): MajorView {
        return MajorView(
            id = major.id,
            code = major.code,
            name = major.name
        )
    }
}