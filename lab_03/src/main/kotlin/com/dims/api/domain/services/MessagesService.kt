package com.dims.api.domain.services

import com.dims.api.controller.MessagesApiService
import com.dims.api.core.Message as DomainMessage
import com.dims.api.core.MessageHistory as DomainMessageHistory
import com.dims.api.data.entities.MessageEntity
import com.dims.api.data.entities.MessageHistoryEntity
import com.dims.api.data.repositories.ChatRepository
import com.dims.api.data.repositories.MessageRepository
import com.dims.api.model.*
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.OffsetDateTime
import java.util.*

@Service
class MessagesServiceImpl(
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository
) : MessagesApiService {

    override fun sendMessage(chatId: UUID, messageCreate: MessageCreate): Message {
        if (!chatRepository.existsById(chatId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found")
        }

        val entity = MessageEntity(
            chatId = chatId,
            content = messageCreate.content,
            fileId = messageCreate.fileId,
            senderId = chatRepository.findByIdOrNull(chatId)!!.creatorId,
            timestamp = OffsetDateTime.now()
        )

        val savedEntity = messageRepository.save(entity)
        return savedEntity.toDomain().toDto()
    }

    override fun getChatMessages(chatId: UUID, limit: Int, beforeTimestamp: OffsetDateTime?): PaginatedMessages {
        if (!chatRepository.existsById(chatId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found")
        }

        val pageable = PageRequest.of(0, limit)
        val messages = if (beforeTimestamp != null) {
            messageRepository.findByChatIdAndTimestampBeforeOrderByTimestampDesc(chatId, beforeTimestamp, pageable)
        } else {
            messageRepository.findByChatIdAndTimestampBeforeOrderByTimestampDesc(chatId, OffsetDateTime.now(), pageable)
        }

        val messageDtos = messages.map { it.toDomain().toDto() }

        // курсор для следующей страницы(timestamp самого старого сообщения)
        val nextCursor = messages.lastOrNull()?.timestamp

        return PaginatedMessages(
            items = messageDtos,
            nextCursor = nextCursor,
            hasNextPage = nextCursor != null && messages.size == limit
        )
    }

    override fun getMessageById(chatId: UUID, messageId: UUID): Message {
        val entity = messageRepository.findByIdAndChatId(messageId, chatId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found in this chat")
        return entity.toDomain().toDto()
    }

    override fun editMessage(chatId: UUID, messageId: UUID, messageUpdate: MessageUpdate): Message {
        val entity = messageRepository.findByIdAndChatId(messageId, chatId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found in this chat")

        // содержимое в историю
        val historyEntry = MessageHistoryEntity(
            message = entity,
            editedContent = entity.content,
            editTimestamp = entity.timestamp
        )
        entity.history.add(historyEntry)

        entity.content = messageUpdate.content
        entity.timestamp = OffsetDateTime.now()

        val savedEntity = messageRepository.save(entity)
        return savedEntity.toDomain().toDto()
    }

    override fun deleteMessage(chatId: UUID, messageId: UUID) {
        if (!messageRepository.existsById(messageId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found")
        }
        messageRepository.deleteById(messageId)
    }

    override fun getMessageHistory(chatId: UUID, messageId: UUID): List<MessageHistory> {
        val entity = messageRepository.findByIdAndChatId(messageId, chatId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found in this chat")

        return entity.history.map { it.toDomain().toDto() }
    }
}

private fun MessageEntity.toDomain(): DomainMessage {
    return DomainMessage(id = id!!, senderId = senderId, chatId = chatId, content = content, fileId = fileId, timestamp = timestamp)
}

private fun DomainMessage.toDto(): Message {
    return Message(id = id, senderId = senderId, chatId = chatId, content = content, fileId = fileId, timestamp = timestamp)
}

private fun MessageHistoryEntity.toDomain(): DomainMessageHistory {
    return DomainMessageHistory(historyId = historyId!!, messageId = message.id!!, editedContent = editedContent, editTimestamp = editTimestamp)
}

private fun DomainMessageHistory.toDto(): MessageHistory {
    return MessageHistory(historyId = historyId, messageId = messageId, editedContent = editedContent, editTimestamp = editTimestamp)
}
