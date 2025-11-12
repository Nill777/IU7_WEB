package com.dims.api.core

import java.util.UUID

data class User(
    val id: UUID,
    val username: String,
    val role: String
)
