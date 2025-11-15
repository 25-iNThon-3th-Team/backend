package com.inthon.kourse.domain.entity

import com.inthon.kourse.common.entity.BaseEntity
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "chat_message")
data class ChatMessage(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    val room: ChatRoom,

    @JoinColumn(name = "sender_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    var sender: User,

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String,

    @Column(name = "sent_at", nullable = false)
    var sentAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "is_read", nullable = false)
    var isRead: Boolean = false
) : BaseEntity()