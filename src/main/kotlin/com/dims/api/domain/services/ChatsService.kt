package com.dims.api.domain.services

import com.dims.api.controller.ChatsApiService
import com.dims.api.core.Chat as DomainChat
import com.dims.api.data.entities.ChatEntity
import com.dims.api.data.repositories.ChatRepository
import com.dims.api.data.repositories.UserRepository
import com.dims.api.model.Chat as ChatDto
import com.dims.api.model.ChatCreate
import com.dims.api.model.ChatUpdate
import com.dims.api.model.PaginatedChats
import com.dims.api.model.PaginationInfo
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class ChatsServiceImpl(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) : ChatsApiService {

    override fun createChat(chatCreate: ChatCreate): ChatDto {
        val creatorId = userRepository.findAll().firstOrNull()?.id
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot create chat, no users exist.")
        val companionId = chatCreate.companionId

        // собеседник существует
        if (!userRepository.existsById(companionId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Companion user with id $companionId not found.")
        }

        // не существует чат
        if (chatCreate.isGroupChat == false) {
            val existingChat = chatRepository.findPrivateChatBetween(creatorId, companionId)
            if (existingChat != null) {
                return existingChat.toDomain().toDto()
            }
        }

        val entity = ChatEntity(
            name = chatCreate.name ?: userRepository.findByIdOrNull(companionId)?.username,
            creatorId = creatorId,
            companionId = companionId,
            isGroupChat = chatCreate.isGroupChat ?: false
        )

        val savedEntity = chatRepository.save(entity)
        return savedEntity.toDomain().toDto()
    }

    override fun deleteChat(chatId: UUID) {
        if (!chatRepository.existsById(chatId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found")
        }
        chatRepository.deleteById(chatId)
    }

    override fun getChatById(chatId: UUID): ChatDto {
        val entity = chatRepository.findByIdOrNull(chatId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found")
        return entity.toDomain().toDto()
    }

    override fun getChats(userId: UUID, page: Int, limit: Int): PaginatedChats {
        val pageable = PageRequest.of(page - 1, limit)
        val chatPage = chatRepository.findChatsForUser(userId, pageable)

        val chatDtos = chatPage.content.map { it.toDomain().toDto() }

        return PaginatedChats(
            items = chatDtos,
            pagination = PaginationInfo(
                totalItems = chatPage.totalElements.toInt(),
                totalPages = chatPage.totalPages,
                currentPage = chatPage.number + 1,
                limit = chatPage.size
            )
        )
    }

    override fun updateChat(chatId: UUID, chatUpdate: ChatUpdate?): ChatDto {
        val entity = chatRepository.findByIdOrNull(chatId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found")

        chatUpdate?.name?.let {
            entity.name = it
        }

        val savedEntity = chatRepository.save(entity)
        return savedEntity.toDomain().toDto()
    }
}

private fun ChatEntity.toDomain(): DomainChat {
    return DomainChat(
        id = this.id!!,
        name = this.name,
        creatorId = this.creatorId,
        isGroupChat = this.isGroupChat,
        companionId = this.companionId
    )
}

private fun DomainChat.toDto(): ChatDto {
    return ChatDto(
        id = this.id,
        name = this.name,
        creatorId = this.creatorId,
        isGroupChat = this.isGroupChat,
        companionId = this.companionId
    )
}
