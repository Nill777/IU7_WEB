package com.dims.api.data.entities

import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "message_history")
class MessageHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val historyId: UUID? = null,

    @Column(nullable = false, length = 4096)
    var editedContent: String,

    @Column(nullable = false)
    var editTimestamp: OffsetDateTime,

    // много записей в истории ссылаются на одно сообщение
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id", nullable = false)
    var message: MessageEntity
)
