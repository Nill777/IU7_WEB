package com.dims.api.data.repositories

import com.dims.api.data.entities.BlockEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BlockRepository : JpaRepository<BlockEntity, UUID> {

    /**
     * Ищет страницу блокировок по ID того, кто блокирует (blockerId)
     */
    fun findByBlockerId(blockerId: UUID, pageable: Pageable): Page<BlockEntity>

    /**
     * Ищет конкретную блокировку по ID блокирующего и ID заблокированного
     */
    fun findByBlockerIdAndBlockedUserId(blockerId: UUID, blockedUserId: UUID): BlockEntity?
}