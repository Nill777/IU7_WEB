package com.dims.api.data.repositories

import com.dims.api.data.entities.ChatEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ChatRepository : JpaRepository<ChatEntity, UUID> {

    @Query("SELECT c FROM ChatEntity c WHERE c.isGroupChat = false AND ((c.creatorId = ?1 AND c.companionId = ?2) OR (c.creatorId = ?2 AND c.companionId = ?1))")
    fun findPrivateChatBetween(user1: UUID, user2: UUID): ChatEntity?

    @Query("SELECT c FROM ChatEntity c WHERE c.creatorId = ?1 OR c.companionId = ?1")
    fun findChatsForUser(userId: UUID, pageable: Pageable): Page<ChatEntity>
}
