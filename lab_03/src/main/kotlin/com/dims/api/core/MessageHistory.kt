package com.dims.api.core

import java.time.OffsetDateTime
import java.util.UUID

data class MessageHistory(
    val historyId: UUID,
    val messageId: UUID,
    val editedContent: String,
    val editTimestamp: OffsetDateTime
)
