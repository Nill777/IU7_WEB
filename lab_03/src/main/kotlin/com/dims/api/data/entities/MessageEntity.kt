package com.dims.api.data.entities

import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "messages")
class MessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false)
    var senderId: UUID,

    @Column(nullable = false)
    var chatId: UUID,

    @Column(nullable = false, length = 4096)
    var content: String,

    var fileId: UUID?,

    @Column(nullable = false)
    var timestamp: OffsetDateTime,

    // одно сообщение может иметь много записей в истории
    @OneToMany(mappedBy = "message", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OrderBy("editTimestamp ASC") // сортируем историю по времени
    var history: MutableList<MessageHistoryEntity> = mutableListOf()
)
