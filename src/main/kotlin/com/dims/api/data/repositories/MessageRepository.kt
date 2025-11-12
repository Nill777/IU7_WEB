package com.dims.api.data.repositories

import com.dims.api.data.entities.MessageEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.UUID

@Repository
interface MessageRepository : JpaRepository<MessageEntity, UUID> {
    // Курсорная пагинация: ищем сообщения в чате, которые были созданы ДО указанной даты,
    // сортируем по убыванию даты и берем первую страницу.
    fun findByChatIdAndTimestampBeforeOrderByTimestampDesc(chatId: UUID, timestamp: OffsetDateTime, pageable: Pageable): List<MessageEntity>

    // То же самое, но для самого первого запроса (когда `before_timestamp` не указан)
    fun findByChatIdOrderByTimestampDesc(chatId: UUID, pageable: Pageable): List<MessageEntity>

    fun findByIdAndChatId(id: UUID, chatId: UUID): MessageEntity?
}
