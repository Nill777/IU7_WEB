package com.dims.api.data.entities

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "chats")
class ChatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    var name: String?,

    @Column(nullable = false)
    var creatorId: UUID,

    @Column(nullable = false)
    var isGroupChat: Boolean = false,

    // ID второго участника, если это не групповой чат
    var companionId: UUID?
)
