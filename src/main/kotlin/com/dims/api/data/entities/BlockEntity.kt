package com.dims.api.data.entities

import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "blocks")
class BlockEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false)
    var blockerId: UUID,

    @Column(nullable = false)
    var blockedUserId: UUID,

    var reason: String?,

    @Column(nullable = false)
    var timestamp: OffsetDateTime
)
