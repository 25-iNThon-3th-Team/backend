package com.inthon.kourse.domain.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.OffsetDateTime

data class ChatRoomView(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val sender: SimpleUserView,
    val receiver: SimpleUserView,
    val lastMessage: String?,
    val lastMessageAt: OffsetDateTime,
    val unreadCount: Long,
    val createdAt: OffsetDateTime
)

data class ChatMessageView(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long,
    val roomId: Long,
    val senderId: Long,
    val senderName: String,
    val content: String,
    val sentAt: OffsetDateTime,
    val isRead: Boolean
)

data class ChatRoomCreateRequest(
    val otherUserId: Long
)

data class ChatMessageSendRequest(
    val content: String
)

data class ChatMessagesPollingRequest(
    val since: OffsetDateTime? = null // Get messages after this timestamp
)

data class ChatMessagesResponse(
    val messages: List<ChatMessageView>,
    val hasMore: Boolean = false
)