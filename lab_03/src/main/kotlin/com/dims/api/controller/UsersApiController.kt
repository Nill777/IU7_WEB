package com.dims.api.controller

import com.dims.api.model.PaginatedUsers
import com.dims.api.model.User
import com.dims.api.model.UserUpdate
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.enums.*
import io.swagger.v3.oas.annotations.media.*
import io.swagger.v3.oas.annotations.responses.*
import io.swagger.v3.oas.annotations.security.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

import kotlin.collections.List
import kotlin.collections.Map

@RestController
@Validated
@RequestMapping("\${api.base-path:/api/v2}")
class UsersApiController(@Autowired(required = true) val service: UsersApiService) {

    @Operation(
        summary = "Удалить пользователя по ID (Admin)",
        operationId = "deleteUser",
        description = """""",
        responses = [
            ApiResponse(responseCode = "204", description = "Пользователь удален."),
            ApiResponse(responseCode = "403", description = "Доступ запрещен."),
            ApiResponse(responseCode = "404", description = "Пользователь не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/users/{userId}"]
    )
    fun deleteUser(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID): ResponseEntity<Unit> {
        return ResponseEntity(service.deleteUser(userId), HttpStatus.valueOf(204))
    }

    @Operation(
        summary = "Получить список всех пользователей (Admin)",
        operationId = "getAllUsers",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Страница со списком пользователей.", content = [Content(schema = Schema(implementation = PaginatedUsers::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users"],
        produces = ["application/json"]
    )
    fun getAllUsers(@Parameter(description = "") @Valid @RequestParam(value = "username", required = false) username: kotlin.String?,@Parameter(description = "Номер страницы", schema = Schema(defaultValue = "1")) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") page: kotlin.Int,@Parameter(description = "Количество элементов на странице", schema = Schema(defaultValue = "25")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "25") limit: kotlin.Int): ResponseEntity<PaginatedUsers> {
        return ResponseEntity(service.getAllUsers(username, page, limit), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Получить пользователя по ID",
        operationId = "getUserById",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Профиль пользователя.", content = [Content(schema = Schema(implementation = User::class))]),
            ApiResponse(responseCode = "404", description = "Пользователь не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{userId}"],
        produces = ["application/json"]
    )
    fun getUserById(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID): ResponseEntity<User> {
        return ResponseEntity(service.getUserById(userId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Обновить пользователя по ID",
        operationId = "updateUser",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Пользователь обновлен.", content = [Content(schema = Schema(implementation = User::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен."),
            ApiResponse(responseCode = "404", description = "Пользователь не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.PATCH],
        value = ["/users/{userId}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUser(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID,@Parameter(description = "") @Valid @RequestBody(required = false) userUpdate: UserUpdate?): ResponseEntity<User> {
        return ResponseEntity(service.updateUser(userId, userUpdate), HttpStatus.valueOf(200))
    }
}
