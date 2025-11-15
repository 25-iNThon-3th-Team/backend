package com.inthon.kourse.domain.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "timetableclass")
@IdClass(TimetableClassId::class)
data class TimetableClass(
    @Id
    @Column(name = "timetable_id")
    val timetableId: Long,

    @Id
    @Column(name = "class_id")
    val classId: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "timetable_id", insertable = false, updatable = false)
    val timetable: Timetable? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    val classEntity: Class? = null
)

data class TimetableClassId(
    val timetableId: Long = 0L,
    val classId: Long = 0L
) : Serializable