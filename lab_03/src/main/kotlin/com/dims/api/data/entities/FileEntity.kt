package com.dims.api.data.entities

import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "files")
class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    var type: String?,

    @Column(nullable = false, unique = true)
    var path: String,

    @Column(nullable = false)
    var uploadedBy: UUID,

    @Column(nullable = false)
    var uploadedTimestamp: OffsetDateTime
)
