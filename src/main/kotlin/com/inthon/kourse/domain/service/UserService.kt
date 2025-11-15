package com.inthon.kourse.domain.service

import com.inthon.kourse.domain.entity.User
import com.inthon.kourse.domain.model.UserCreateRequest
import com.inthon.kourse.domain.model.UserUpdateRequest
import com.inthon.kourse.domain.model.UserView
import com.inthon.kourse.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun createUser(request: UserCreateRequest): UserView {
        if (userRepository.findByUsername(request.username) != null) {
            throw IllegalArgumentException("Username already exists")
        }

        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            enabled = true,
            roles = listOf("USER"),
            grade = request.grade,
            semester = request.semester,
            majorCode = request.majorCode,
            creditsMajorRequired = request.creditsMajorRequired,
            creditsMajorElective = request.creditsMajorElective,
            creditsGeneral = request.creditsGeneral,
            preferredOffDays = request.preferredOffDays,
            preferredTimeSlot = request.preferredTimeSlot,
            maxTransferMinutes = request.maxTransferMinutes,
            priorityOrder = request.priorityOrder
        )

        val savedUser = userRepository.save(user)
        return toUserView(savedUser)
    }

    @Transactional(readOnly = true)
    fun getUserById(id: Long): UserView {
        val user = userRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("User not found with id: $id")
        return toUserView(user)
    }

    @Transactional(readOnly = true)
    fun getUserByUsername(username: String): UserView {
        val user = userRepository.findByUsername(username)
            ?: throw NoSuchElementException("User not found with username: $username")
        return toUserView(user)
    }

    @Transactional(readOnly = true)
    fun getAllUsers(): List<UserView> {
        return userRepository.findAll().map { toUserView(it) }
    }

    @Transactional
    fun updateUser(id: Long, request: UserUpdateRequest): UserView {
        val user = userRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("User not found with id: $id")

        user.apply {
            username = request.username ?: user.username
            password = user.password
            grade = request.grade ?: user.grade
            semester = request.semester ?: user.semester
            majorCode = request.majorCode ?: user.majorCode
            creditsMajorRequired = request.creditsMajorRequired ?: user.creditsMajorRequired
            creditsMajorElective = request.creditsMajorElective ?: user.creditsMajorElective
            creditsGeneral = request.creditsGeneral ?: user.creditsGeneral
            preferredOffDays = request.preferredOffDays ?: user.preferredOffDays
            preferredTimeSlot = request.preferredTimeSlot ?: user.preferredTimeSlot
            maxTransferMinutes = request.maxTransferMinutes ?: user.maxTransferMinutes
            priorityOrder = request.priorityOrder ?: user.priorityOrder
        }

        val savedUser = userRepository.save(user)
        return toUserView(savedUser)
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw NoSuchElementException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }

    private fun toUserView(user: User): UserView {
        return UserView(
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
}