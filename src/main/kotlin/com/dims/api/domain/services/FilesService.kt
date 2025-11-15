package com.dims.api.domain.services

import com.dims.api.controller.FilesApiService
import com.dims.api.core.File as DomainFile
import com.dims.api.data.entities.FileEntity
import com.dims.api.data.repositories.FileRepository
import com.dims.api.model.File as FileDto
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.net.MalformedURLException
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.OffsetDateTime
import java.util.UUID

@Service
class FilesServiceImpl(
    private val fileRepository: FileRepository
) : FilesApiService {

    @Value("\${file.upload-dir}")
    private lateinit var uploadDir: String

    private lateinit var rootLocation: Path

    // выполнится один раз после создания сервиса
    @PostConstruct
    fun init() {
        rootLocation = Paths.get(uploadDir)
        if (Files.notExists(rootLocation)) {
            Files.createDirectories(rootLocation)
        }
    }

    override fun uploadFile(file: MultipartFile?): FileDto {
        if (file == null || file.isEmpty) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty")
        }

        val originalFilename = file.originalFilename ?: "file"
        val storedFilename = "${UUID.randomUUID()}-${originalFilename}"
        val destinationFile = rootLocation.resolve(storedFilename).normalize().toAbsolutePath()

        // копируем файл в хранилище
        file.inputStream.use { inputStream ->
            Files.copy(inputStream, destinationFile)
        }

        val entity = FileEntity(
            name = originalFilename,
            type = file.contentType,
            path = storedFilename,
            uploadedBy = UUID.randomUUID(),
            uploadedTimestamp = OffsetDateTime.now()
        )

        val savedEntity = fileRepository.save(entity)
        return savedEntity.toDomain().toDto()
    }

    override fun getFileMetadata(fileId: UUID): FileDto {
        val entity = fileRepository.findByIdOrNull(fileId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "File not found")

        return entity.toDomain().toDto()
    }

    override fun downloadFile(fileId: UUID): Resource {
        val entity = fileRepository.findByIdOrNull(fileId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "File metadata not found")

        try {
            val file = rootLocation.resolve(entity.path)
            val resource = UrlResource(file.toUri())

            if (resource.exists() && resource.isReadable) {
                return resource
            } else {
                throw RuntimeException("Could not read the file!")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("Error: " + e.message)
        }
    }

    override fun deleteFile(fileId: UUID) {
        val entity = fileRepository.findByIdOrNull(fileId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "File not found")

        // удаляем файл с диска
        val fileOnDisk = rootLocation.resolve(entity.path)
        Files.deleteIfExists(fileOnDisk)

        fileRepository.deleteById(fileId)
    }
}

private fun FileEntity.toDomain(): DomainFile {
    return DomainFile(
        id = this.id!!,
        name = this.name,
        type = this.type,
        path = this.path,
        uploadedBy = this.uploadedBy,
        uploadedTimestamp = this.uploadedTimestamp
    )
}

private fun DomainFile.toDto(): FileDto {
    return FileDto(
        id = this.id,
        name = this.name,
        type = this.type,
        // DTO требует URI, создаем его из пути
        path = URI.create("/api/v2/files/${this.id}"),
        uploadedBy = this.uploadedBy,
        uploadedTimestamp = this.uploadedTimestamp
    )
}
