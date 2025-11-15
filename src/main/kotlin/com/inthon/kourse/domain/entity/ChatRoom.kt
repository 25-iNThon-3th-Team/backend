package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(
    name = "chat_room",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["sender", "retriever"])
    ]
)
data class ChatRoom(
    @JoinColumn(name = "sender", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    var sender: User,

    @JoinColumn(name = "retriever", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    var retriever: User,

    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "last_message_at", nullable = false)
    var lastMessageAt: OffsetDateTime = OffsetDateTime.now()
) : BaseEntity()