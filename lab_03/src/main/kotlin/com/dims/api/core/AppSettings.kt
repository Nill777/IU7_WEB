package com.dims.api.core

import java.util.UUID

data class AppSettings(
    val id: UUID,
    var theme: Int,
    var maxFileSize: Int,
    var messageEncryption: Int,
    var historyStorage: Int
)
