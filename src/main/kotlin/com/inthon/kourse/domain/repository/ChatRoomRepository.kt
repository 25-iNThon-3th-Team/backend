package com.inthon.kourse.domain.repository

import com.inthon.kourse.domain.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
    @Query("SELECT cr FROM ChatRoom cr WHERE (cr.sender.id = :userId or cr.retriever.id = :userId) ORDER BY cr.lastMessageAt DESC")
    fun findBySenderIdOrderByLastMessageAtDesc(@Param("userId") userId: Long): List<ChatRoom>

    @Query("SELECT cr FROM ChatRoom cr WHERE (cr.sender.id = :senderId AND cr.retriever.id = :retrieverId)")
    fun findByTwoUsers(@Param("senderId") senderId: Long, @Param("retrieverId") retrieverId: Long): ChatRoom?
}