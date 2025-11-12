package com.dims.api.core

import java.time.OffsetDateTime
import java.util.UUID

data class Block(
    val id: UUID,
    val blockerId: UUID,
    val blockedUserId: UUID,
    val reason: String?,
    val timestamp: OffsetDateTime
)
