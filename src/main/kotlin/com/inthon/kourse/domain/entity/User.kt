package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import com.inthon.kourse.common.entity.TimestampedEntity
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Type

@Entity
@Table(name = "users")
data class User(

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val enabled: Boolean = true,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val roles: List<String> = listOf("USER"),

    // 1. Grade / Semester
    @Column(name = "grade")
    val grade: Short? = null,

    @Column(name = "semester")
    val semester: Short? = null,

    // 2. Major selection
    @Column(name = "major_code", length = 100)
    val majorCode: String? = null,

    // 3. Current credits
    @Column(name = "credits_major_required", nullable = false)
    val creditsMajorRequired: Int = 0,

    @Column(name = "credits_major_elective", nullable = false)
    val creditsMajorElective: Int = 0,

    @Column(name = "credits_general", nullable = false)
    val creditsGeneral: Int = 0,

    // 4. Time constraints
    @Type(JsonType::class)
    @Column(name = "preferred_off_days", columnDefinition = "jsonb")
    val preferredOffDays: List<String>? = null,

    @Column(name = "preferred_time_slot", length = 10)
    val preferredTimeSlot: String = "NONE",

    @Column(name = "max_transfer_minutes")
    val maxTransferMinutes: Short = 20,

    // 5. Priority settings
    @Type(JsonType::class)
    @Column(name = "priority_order", columnDefinition = "jsonb")
    val priorityOrder: List<String>? = null

) : TimestampedEntity()