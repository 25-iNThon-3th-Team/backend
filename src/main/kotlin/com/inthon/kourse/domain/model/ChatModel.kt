package com.inthon.kourse.domain.model

data class ChatRequest(
    val message: String,
    val conversationHistory: List<ChatMessage>? = null
)

data class ChatMessage(
    val role: String,
    val content: String
)

data class ChatResponse(
    val reply: String,
    val conversationHistory: List<ChatMessage>
)