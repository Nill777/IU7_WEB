package com.dims.api.core

import java.time.OffsetDateTime
import java.util.UUID

data class Message(
    val id: UUID,
    val senderId: UUID,
    val chatId: UUID,
    val content: String,
    val fileId: UUID?,
    val timestamp: OffsetDateTime
)
