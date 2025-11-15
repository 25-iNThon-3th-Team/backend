package com.inthon.kourse.domain.service

import com.inthon.kourse.common.DomainMapper
import com.inthon.kourse.domain.entity.ChatMessage
import com.inthon.kourse.domain.entity.ChatRoom
import com.inthon.kourse.domain.model.*
import com.inthon.kourse.domain.repository.ChatMessageRepository
import com.inthon.kourse.domain.repository.ChatRoomRepository
import com.inthon.kourse.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Service
@Transactional(readOnly = true)
class ChatService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val userRepository: UserRepository,
    private val domainMapper: DomainMapper
) {

    fun getChatRoomById(roomId: Long): ChatRoomView {
        val room = chatRoomRepository.findByIdOrNull(roomId)
            ?: throw NoSuchElementException("Chat room not found with id: $roomId")
        return toChatRoomView(room, -1)
    }

    fun getChatRoomsByUserId(userId: Long): List<ChatRoomView> {
        return chatRoomRepository.findBySenderIdOrderByLastMessageAtDesc(userId)
            .map { toChatRoomView(it, userId) }
    }

    @Transactional
    fun getOrCreateChatRoom(senderId: Long, retrieverId: Long): ChatRoomView {
        if (senderId == retrieverId) {
            throw IllegalArgumentException("Cannot create chat room with yourself")
        }

        val existingRoom = chatRoomRepository.findByTwoUsers(senderId, retrieverId)
        if (existingRoom != null) {
            return toChatRoomView(existingRoom, senderId)
        }

        val senderUser = userRepository.findByIdOrNull(senderId) ?: throw NoSuchElementException("No sender found with id: $retrieverId")
        val retrieverUser = userRepository.findByIdOrNull(retrieverId) ?: throw NoSuchElementException("No retriever found with id: $retrieverId")

        // Create new room
        val newRoom = ChatRoom(
            sender = senderUser,
            retriever = retrieverUser,
        )

        val savedRoom = chatRoomRepository.save(newRoom)
        return toChatRoomView(savedRoom, senderId)
    }

    fun getMessages(roomId: Long, userId: Long): List<ChatMessageView> {
        val room = chatRoomRepository.findByIdOrNull(roomId)
            ?: throw NoSuchElementException("Chat room not found with id: $roomId")

        // Verify user is participant
        if (room.sender.id != userId && room.retriever.id != userId) {
            throw IllegalAccessException("You are not a participant in this chat room")
        }

        return chatMessageRepository.findByRoomOrderBySentAtAsc(room)
            .map { toChatMessageView(it) }
    }

    fun getMessagesSince(roomId: Long, userId: Long, since: OffsetDateTime): ChatMessagesResponse {
        val room = chatRoomRepository.findByIdOrNull(roomId)
            ?: throw NoSuchElementException("Chat room not found with id: $roomId")

        // Verify user is participant
        if (room.sender.id != userId && room.retriever.id != userId) {
            throw IllegalAccessException("You are not a participant in this chat room")
        }

        val messages = chatMessageRepository.findMessagesSince(room, since)
            .map { toChatMessageView(it) }

        return ChatMessagesResponse(
            messages = messages,
            hasMore = false
        )
    }

    @Transactional
    fun sendMessage(roomId: Long, senderId: Long, content: String): ChatMessageView {
        val room = chatRoomRepository.findByIdOrNull(roomId)
            ?: throw NoSuchElementException("Chat room not found with id: $roomId")
        val user = userRepository.findByIdOrNull(senderId) ?: throw NoSuchElementException("No sender found with id: $senderId")

        // Verify user is participant
        if (room.sender.id != senderId && room.retriever.id != senderId) {
            throw IllegalAccessException("You are not a participant in this chat room")
        }

        val message = ChatMessage(
            room = room,
            sender = user,
            content = content,
            sentAt = OffsetDateTime.now(),
            isRead = false
        )

        val savedMessage = chatMessageRepository.save(message)

        // Update room's last message timestamp
        room.apply {
            lastMessageAt = savedMessage.sentAt
        }
        chatRoomRepository.save(room)

        return toChatMessageView(savedMessage)
    }

    @Transactional
    fun markMessagesAsRead(roomId: Long, userId: Long): Int {
        val room = chatRoomRepository.findByIdOrNull(roomId)
            ?: throw NoSuchElementException("Chat room not found with id: $roomId")

        // Verify user is participant
        if (room.sender.id != userId && room.retriever.id != userId) {
            throw IllegalAccessException("You are not a participant in this chat room")
        }

        return chatMessageRepository.markMessagesAsRead(room, userId)
    }

    @Transactional
    fun deleteChatRoom(roomId: Long, userId: Long) {
        val room = chatRoomRepository.findByIdOrNull(roomId)
            ?: throw NoSuchElementException("Chat room not found with id: $roomId")

        // Verify user is participant
        if (room.sender.id != userId && room.retriever.id != userId) {
            throw IllegalAccessException("You are not a participant in this chat room")
        }

        chatRoomRepository.deleteById(roomId)
    }

    private fun toChatRoomView(room: ChatRoom, userId: Long): ChatRoomView {

        // Get last message
        val messages = chatMessageRepository.findByRoomOrderBySentAtAsc(room)
        val lastMessage = messages.lastOrNull()

        // Count unread messages from the other user
        val unreadCount = chatMessageRepository.countByRoomAndIsReadFalseAndSenderIdNot(room, userId)

        return ChatRoomView(
            id = room.id,
            sender = room.sender.let { domainMapper.toSimpleView(it) },
            receiver = room.retriever.let { domainMapper.toSimpleView(it) },
            lastMessage = lastMessage?.content,
            lastMessageAt = room.lastMessageAt,
            unreadCount = unreadCount,
            createdAt = room.createdAt
        )
    }

    private fun toChatMessageView(message: ChatMessage): ChatMessageView {

        return ChatMessageView(
            id = message.id,
            roomId = message.room.id,
            senderId = message.sender.id,
            senderName = message.sender.username,
            content = message.content,
            sentAt = message.sentAt,
            isRead = message.isRead
        )
    }
}