package com.inthon.kourse.domain.controller

import com.inthon.kourse.domain.model.*
import com.inthon.kourse.domain.service.ChatService
import com.inthon.kourse.system.security.model.CustomUserDetails
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatService: ChatService
) {

    @GetMapping("/rooms")
    fun getMyChatRooms(@AuthenticationPrincipal principal: CustomUserDetails): ResponseEntity<List<ChatRoomView>> {
        val rooms = chatService.getChatRoomsByUserId(principal.getUser().id)
        return ResponseEntity.ok(rooms)
    }

    @GetMapping("/rooms/{roomId}")
    fun getChatRoom(
        @PathVariable roomId: Long,
        @AuthenticationPrincipal principal: CustomUserDetails
    ): ResponseEntity<ChatRoomView> {
        val room = chatService.getChatRoomById(roomId)

        // Verify user is participant
        if (room.sender.id != principal.getUser().id && room.receiver.id != principal.getUser().id) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }

        return ResponseEntity.ok(room)
    }

    @PostMapping("/rooms")
    fun createOrGetChatRoom(
        @AuthenticationPrincipal principal: CustomUserDetails,
        @RequestBody request: ChatRoomCreateRequest
    ): ResponseEntity<ChatRoomView> {
        val room = chatService.getOrCreateChatRoom(principal.getUser().id, request.otherUserId)
        return ResponseEntity.status(HttpStatus.CREATED).body(room)
    }

    @DeleteMapping("/rooms/{roomId}")
    fun deleteChatRoom(
        @PathVariable roomId: Long,
        @AuthenticationPrincipal principal: CustomUserDetails
    ): ResponseEntity<Void> {
        chatService.deleteChatRoom(roomId, principal.getUser().id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/rooms/{roomId}/messages")
    fun getMessages(
        @PathVariable roomId: Long,
        @AuthenticationPrincipal principal: CustomUserDetails
    ): ResponseEntity<List<ChatMessageView>> {
        val messages = chatService.getMessages(roomId, principal.getUser().id)
        return ResponseEntity.ok(messages)
    }

    @GetMapping("/rooms/{roomId}/messages/poll")
    fun pollMessages(
        @PathVariable roomId: Long,
        @RequestParam(required = false) since: String?,
        @AuthenticationPrincipal principal: CustomUserDetails
    ): ResponseEntity<ChatMessagesResponse> {
        val sinceTime = since?.let { OffsetDateTime.parse(it) } ?: OffsetDateTime.now().minusMinutes(5)
        val response = chatService.getMessagesSince(roomId, principal.getUser().id, sinceTime)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/rooms/{roomId}/messages")
    fun sendMessage(
        @PathVariable roomId: Long,
        @AuthenticationPrincipal principal: CustomUserDetails,
        @RequestBody request: ChatMessageSendRequest
    ): ResponseEntity<ChatMessageView> {
        val message = chatService.sendMessage(roomId, principal.getUser().id, request.content)
        return ResponseEntity.status(HttpStatus.CREATED).body(message)
    }

    @PostMapping("/rooms/{roomId}/read")
    fun markMessagesAsRead(
        @PathVariable roomId: Long,
        @AuthenticationPrincipal principal: CustomUserDetails
    ): ResponseEntity<Map<String, Int>> {
        val count = chatService.markMessagesAsRead(roomId, principal.getUser().id)
        return ResponseEntity.ok(mapOf("markedAsRead" to count))
    }
}