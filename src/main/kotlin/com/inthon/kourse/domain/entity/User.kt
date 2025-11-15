package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.TimestampedEntity
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Type

@Entity
@Table(name = "users")
class User(

    @Column(unique = true, nullable = false)
    var userId: String,

    @Column(nullable = false)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var enabled: Boolean = true,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    var roles: List<String> = listOf(),

    // 1. Grade / Semester
    @Column(name = "grade")
    var grade: Short? = null,

    @Column(name = "semester")
    var semester: Short? = null,

    // 2. Major selection
    @Column(name = "major_code", length = 100)
    var majorCode: String? = null,

    // 3. Current credits
    @Column(name = "credits_major_required", nullable = false)
    var creditsMajorRequired: Int = 0,

    @Column(name = "credits_major_elective", nullable = false)
    var creditsMajorElective: Int = 0,

    @Column(name = "credits_general", nullable = false)
    var creditsGeneral: Int = 0,

    // 4. Time constraints
    @Type(JsonType::class)
    @Column(name = "preferred_off_days", columnDefinition = "jsonb")
    var preferredOffDays: List<String>? = null,

    @Column(name = "preferred_time_slot", length = 10)
    var preferredTimeSlot: String = "NONE",

    @Column(name = "max_transfer_minutes")
    var maxTransferMinutes: Short = 20,

    // 5. Priority settings
    @Type(JsonType::class)
    @Column(name = "priority_order", columnDefinition = "jsonb")
    var priorityOrder: List<String>? = null

) : TimestampedEntity()