package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import com.inthon.kourse.common.entity.PrimaryKeyEntity
import io.hypersistence.tsid.TSID
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "class")
data class Class(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    val course: Course,

    @Column(name = "class_code", nullable = false, length = 20)
    val classCode: String = "01",

    @Column(name = "professor_name", length = 255)
    val professorName: String? = null,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val schedule: List<Map<String, String>>? = null,

    @Column(name = "total_seats")
    val totalSeats: Int = 50
) : BaseEntity()