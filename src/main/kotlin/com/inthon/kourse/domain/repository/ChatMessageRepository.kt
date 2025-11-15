package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.ChatMessage
import com.inthon.kourse.domain.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessage, Long> {
    fun findByRoomOrderBySentAtAsc(room: ChatRoom): List<ChatMessage>

    fun findByRoomAndSentAtAfterOrderBySentAtAsc(room: ChatRoom, sentAt: OffsetDateTime): List<ChatMessage>

    @Query("SELECT cm FROM ChatMessage cm WHERE cm.room = :room AND cm.sentAt > :since ORDER BY cm.sentAt ASC")
    fun findMessagesSince(@Param("room") room: ChatRoom, @Param("since") since: OffsetDateTime): List<ChatMessage>

    @Modifying
    @Query("UPDATE ChatMessage cm SET cm.isRead = true WHERE cm.room = :room AND cm.sender.id != :userId AND cm.isRead = false")
    fun markMessagesAsRead(@Param("room") room: ChatRoom, @Param("userId") userId: Long): Int

    fun countByRoomAndIsReadFalseAndSenderIdNot(room: ChatRoom, senderId: Long): Long
}