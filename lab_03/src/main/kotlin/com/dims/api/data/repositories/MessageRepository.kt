package com.dims.api.data.repositories

import com.dims.api.data.entities.MessageEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.UUID

@Repository
interface MessageRepository : JpaRepository<MessageEntity, UUID> {
    // Курсорная пагинация - ищем сообщения в чате, которые были созданы до указанной даты,
    // сортируем по убыванию даты и берем первую страницу
    // что-то в духе + пагинация
    // SELECT m FROM MessageEntity m
    // WHERE m.chatId = :chatId AND m.timestamp < :timestamp
    // ORDER BY m.timestamp DESC
    fun findByChatIdAndTimestampBeforeOrderByTimestampDesc(chatId: UUID, timestamp: OffsetDateTime, pageable: Pageable): List<MessageEntity>

    // То же самое, но для самого первого запроса (когда timestamp не указан)
    fun findByChatIdOrderByTimestampDesc(chatId: UUID, pageable: Pageable): List<MessageEntity>

    fun findByIdAndChatId(id: UUID, chatId: UUID): MessageEntity?
}
