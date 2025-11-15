package com.dims.api.domain.services

import com.dims.api.controller.SettingsApiService
import com.dims.api.core.AppSettings as DomainAppSettings
import com.dims.api.data.entities.AppSettingsEntity
import com.dims.api.data.repositories.UserRepository
import com.dims.api.model.AppSettings as AppSettingsDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class SettingsServiceImpl(
    private val userRepository: UserRepository
) : SettingsApiService {

    override fun getAppSettings(userId: UUID): AppSettingsDto {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        // настройки через связь в UserEntity
        val settingsEntity = user.appSettings
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Settings not found for user")

        return settingsEntity.toDomain().toDto()
    }

    override fun updateAppSettings(userId: UUID, appSettings: AppSettingsDto?): AppSettingsDto {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

        val settingsToUpdate = user.appSettings
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Settings not found for user")

        appSettings?.let { dto ->
            dto.theme?.let { settingsToUpdate.theme = it.value }
            dto.maxFileSize?.let { settingsToUpdate.maxFileSize = it.value }
            dto.messageEncryption?.let { settingsToUpdate.messageEncryption = it.value }
            dto.historyStorage?.let { settingsToUpdate.historyStorage = it.value }
        }

        // сохраняем User, а JPA используя CascadeType.ALL автоматически сохранит изменения в AppSettings
        val updatedUser = userRepository.save(user)

        return updatedUser.appSettings!!.toDomain().toDto()
    }
}

private fun AppSettingsEntity.toDomain(): DomainAppSettings {
    return DomainAppSettings(
        id = this.id!!,
        theme = this.theme,
        maxFileSize = this.maxFileSize,
        messageEncryption = this.messageEncryption,
        historyStorage = this.historyStorage
    )
}

private fun DomainAppSettings.toDto(): AppSettingsDto {
    return AppSettingsDto(
        theme = AppSettingsDto.THEME.forValue(this.theme),
        maxFileSize = AppSettingsDto.MAXFILESIZE.forValue(this.maxFileSize),
        messageEncryption = AppSettingsDto.MESSAGEENCRYPTION.forValue(this.messageEncryption),
        historyStorage = AppSettingsDto.HISTORYSTORAGE.forValue(this.historyStorage)
    )
}
