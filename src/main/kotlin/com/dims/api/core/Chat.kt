package com.dims.api.core

import java.util.UUID

data class Chat(
    val id: UUID,
    val name: String?,
    val creatorId: UUID,
    val isGroupChat: Boolean,
    val companionId: UUID?
)
