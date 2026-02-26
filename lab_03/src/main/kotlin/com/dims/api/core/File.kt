package com.dims.api.core

import java.net.URI
import java.time.OffsetDateTime
import java.util.UUID

data class File(
    val id: UUID,
    val name: String,
    val type: String?,
    val path: String, // Путь, по которому файл сохранен на сервере
    val uploadedBy: UUID,
    val uploadedTimestamp: OffsetDateTime
)
