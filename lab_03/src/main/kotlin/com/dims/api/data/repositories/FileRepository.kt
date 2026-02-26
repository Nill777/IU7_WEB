package com.dims.api.data.repositories

import com.dims.api.data.entities.FileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FileRepository : JpaRepository<FileEntity, UUID>