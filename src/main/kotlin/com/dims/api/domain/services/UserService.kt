package com.dims.api.domain.services

import com.dims.api.config.JwtTokenUtil
import com.dims.api.controller.AuthenticationApiService
import com.dims.api.controller.UsersApiService
import com.dims.api.data.entities.AppSettingsEntity
import com.dims.api.data.entities.UserEntity
import com.dims.api.data.repositories.UserRepository
import com.dims.api.model.AuthToken
import com.dims.api.model.PaginatedUsers
import com.dims.api.model.PaginationInfo
import com.dims.api.model.UserCreate
import com.dims.api.model.UserUpdate
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import com.dims.api.core.User as DomainUser
import com.dims.api.model.User as UserDto

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil
) : AuthenticationApiService, UsersApiService {

    // --- AuthenticationApiService ---

    override fun registerUser(userCreate: UserCreate): UserDto {
        if (userRepository.findByUsername(userCreate.username) != null) {
            throw ResponseStatusException(
                HttpStatus.CONFLICT,
                "User with username '${userCreate.username}' already exists."
            )
        }

        val userEntity = UserEntity(
            username = userCreate.username,
            password = passwordEncoder.encode(userCreate.password),
            role = userCreate.role?.value ?: "USER"
        )

        // Создаем настройки по умолчанию и связываем их с пользователем
        val defaultSettings = AppSettingsEntity()
        userEntity.appSettings = defaultSettings
        defaultSettings.user = userEntity

        val savedEntity = userRepository.save(userEntity)
        return savedEntity.toDomain().toDto()
    }

    override fun loginUser(userLogin: com.dims.api.model.UserLogin): com.dims.api.model.AuthToken {
        // Аутентифицируем пользователя. Если пароль/логин неверны, здесь выбросится исключение
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(userLogin.username, userLogin.password)
        )

        // Если аутентификация прошла, ищем пользователя и генерируем токен
        val user = userRepository.findByUsername(userLogin.username)
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User disappeared after authentication")

        val token = jwtTokenUtil.generateToken(user)

        return AuthToken(accessToken = token, userId = user.id)
    }

    // --- UsersApiService ---

    override fun getUserById(userId: UUID): UserDto {
        val userEntity = userRepository.findByIdOrNull(userId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: $userId")

        return userEntity.toDomain().toDto()
    }

    override fun deleteUser(userId: UUID) {
        // Сначала проверяем, существует ли пользователь, чтобы вернуть правильную ошибку
        if (!userRepository.existsById(userId)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: $userId")
        }
        userRepository.deleteById(userId)
        // Метод ничего не возвращает, контроллер отправит ответ 204 No Content
    }

    override fun getAllUsers(username: String?, page: Int, limit: Int): PaginatedUsers {
        // Spring Data JPA использует нумерацию страниц с 0, а API обычно с 1.
        // Поэтому отнимаем единицу от номера страницы.
        val pageable: Pageable = PageRequest.of(page - 1, limit)

        // Выбираем, какой метод репозитория использовать, в зависимости от того,
        // был ли передан параметр для фильтрации по имени.
        val userPage = if (username.isNullOrBlank()) {
            userRepository.findAll(pageable)
        } else {
            userRepository.findByUsernameContaining(username, pageable)
        }

        // Конвертируем каждую найденную сущность в DTO
        val userDtos = userPage.content.map { it.toDomain().toDto() }

        // Собираем DTO для ответа, включая информацию о пагинации
        return PaginatedUsers(
            items = userDtos,
            pagination = PaginationInfo(
                totalItems = userPage.totalElements.toInt(),
                totalPages = userPage.totalPages,
                currentPage = userPage.number + 1, // Возвращаем 1-based номер страницы
                limit = userPage.size
            )
        )
    }

    override fun updateUser(userId: UUID, userUpdate: UserUpdate?): UserDto {
        // Находим существующего пользователя в БД. Если не найден - ошибка 404.
        val existingUser = userRepository.findByIdOrNull(userId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: $userId")

        // Применяем изменения, если они были переданы в DTO
        userUpdate?.let { update ->
            // Обновляем имя, если оно было передано
            update.username?.let { newUsername ->
                // Дополнительная проверка: новое имя не должно быть занято другим пользователем
                userRepository.findByUsername(newUsername)?.let {
                    if (it.id != userId) {
                        throw ResponseStatusException(HttpStatus.CONFLICT, "Username '$newUsername' is already taken.")
                    }
                }
                existingUser.username = newUsername
            }
            // Обновляем роль, если она была передана
            update.role?.let { newRole ->
                existingUser.role = newRole.value
            }
        }

        // Сохраняем обновленную сущность и возвращаем ее в виде DTO
        val savedEntity = userRepository.save(existingUser)
        return savedEntity.toDomain().toDto()
    }
}

// --- Мапперы ---

fun UserEntity.toDomain(): DomainUser {
    return DomainUser(
        id = this.id!!,
        username = this.username,
        role = this.role
    )
}

fun DomainUser.toDto(): UserDto {
    return UserDto(
        id = this.id,
        username = this.username,
        role = UserDto.Role.valueOf(this.role)
    )
}
