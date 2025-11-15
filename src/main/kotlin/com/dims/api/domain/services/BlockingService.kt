package com.dims.api.domain.services

import com.dims.api.controller.BlockingApiService
import com.dims.api.data.entities.BlockEntity
import com.dims.api.data.repositories.BlockRepository
import com.dims.api.data.repositories.UserRepository
import com.dims.api.model.Block as BlockDto
import com.dims.api.core.Block as DomainBlock
import com.dims.api.model.BlockCreate
import com.dims.api.model.PaginatedBlocks
import com.dims.api.model.PaginationInfo
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.OffsetDateTime
import java.util.UUID

@Service
class BlockingService(
    private val blockRepository: BlockRepository,
    private val userRepository: UserRepository
) : BlockingApiService {

    override fun blockUser(userId: UUID, blockCreate: BlockCreate): BlockDto {
        val blockerId = userId
        val blockedUserId = blockCreate.blockedUserId

        // пользователь не может заблокировать сам себя
        if (blockerId == blockedUserId) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannot block themselves.")
        }

        // оба пользователя существуют
        if (!userRepository.existsById(blockerId) || !userRepository.existsById(blockedUserId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "One or both users not found.")
        }

        // такой блокировки еще нет
        if (blockRepository.findByBlockerIdAndBlockedUserId(blockerId, blockedUserId) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "This user is already blocked.")
        }

        val entity = BlockEntity(
            blockerId = blockerId,
            blockedUserId = blockedUserId,
            reason = blockCreate.reason,
            timestamp = OffsetDateTime.now()
        )

        val savedEntity = blockRepository.save(entity)
        return savedEntity.toDomain().toDto()
    }

    override fun getBlockedUsers(userId: UUID, page: Int, limit: Int): PaginatedBlocks {
        val pageable = PageRequest.of(page - 1, limit)
        val blockPage = blockRepository.findByBlockerId(userId, pageable)

        val blockDtos = blockPage.content.map { it.toDomain().toDto() }

        return PaginatedBlocks(
            items = blockDtos,
            pagination = PaginationInfo(
                totalItems = blockPage.totalElements.toInt(),
                totalPages = blockPage.totalPages,
                currentPage = blockPage.number + 1,
                limit = blockPage.size
            )
        )
    }

    override fun unblockUser(userId: UUID, blockedUserId: UUID) {
        val block = blockRepository.findByBlockerIdAndBlockedUserId(userId, blockedUserId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Block record not found.")

        blockRepository.delete(block)
    }
}

fun BlockEntity.toDomain(): DomainBlock {
    return DomainBlock(
        id = this.id!!,
        blockerId = this.blockerId,
        blockedUserId = this.blockedUserId,
        reason = this.reason,
        timestamp = this.timestamp
    )
}

fun DomainBlock.toDto(): BlockDto {
    return BlockDto(
        id = this.id,
        blockerId = this.blockerId,
        blockedUserId = this.blockedUserId,
        reason = this.reason,
        timestamp = this.timestamp
    )
}
