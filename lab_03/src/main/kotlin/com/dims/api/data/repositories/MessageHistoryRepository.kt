package com.dims.api.data.repositories

import com.dims.api.data.entities.MessageHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MessageHistoryRepository : JpaRepository<MessageHistoryEntity, UUID>
