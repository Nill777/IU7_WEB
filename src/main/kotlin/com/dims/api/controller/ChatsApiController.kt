package com.dims.api.controller

import com.dims.api.model.Chat
import com.dims.api.model.ChatCreate
import com.dims.api.model.ChatUpdate
import com.dims.api.model.PaginatedChats
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
class ChatsApiController(@Autowired(required = true) val service: ChatsApiService) {

    @Operation(
        summary = "Создать новый чат",
        operationId = "createChat",
        description = """""",
        responses = [
            ApiResponse(responseCode = "201", description = "Чат создан.", content = [Content(schema = Schema(implementation = Chat::class))]) ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/chats"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createChat(@Parameter(description = "", required = true) @Valid @RequestBody chatCreate: ChatCreate): ResponseEntity<Chat> {
        return ResponseEntity(service.createChat(chatCreate), HttpStatus.valueOf(201))
    }

    @Operation(
        summary = "Удалить чат",
        operationId = "deleteChat",
        description = """""",
        responses = [
            ApiResponse(responseCode = "204", description = "Чат удален."),
            ApiResponse(responseCode = "404", description = "Чат не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/chats/{chatId}"]
    )
    fun deleteChat(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID): ResponseEntity<Unit> {
        return ResponseEntity(service.deleteChat(chatId), HttpStatus.valueOf(204))
    }

    @Operation(
        summary = "Получить чат по ID",
        operationId = "getChatById",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Информация о чате.", content = [Content(schema = Schema(implementation = Chat::class))]),
            ApiResponse(responseCode = "404", description = "Чат не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/chats/{chatId}"],
        produces = ["application/json"]
    )
    fun getChatById(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID): ResponseEntity<Chat> {
        return ResponseEntity(service.getChatById(chatId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Получить список чатов",
        operationId = "getChats",
        description = """Возвращает список чатов. Требуется фильтрация по `userId`.""",
        responses = [
            ApiResponse(responseCode = "200", description = "Страница со списком чатов.", content = [Content(schema = Schema(implementation = PaginatedChats::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/chats"],
        produces = ["application/json"]
    )
    fun getChats(@NotNull @Parameter(description = "Фильтр для получения чатов конкретного пользователя.", required = true) @Valid @RequestParam(value = "userId", required = true) userId: java.util.UUID,@Parameter(description = "", schema = Schema(defaultValue = "1")) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") page: kotlin.Int,@Parameter(description = "", schema = Schema(defaultValue = "25")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "25") limit: kotlin.Int): ResponseEntity<PaginatedChats> {
        return ResponseEntity(service.getChats(userId, page, limit), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Обновить название чата",
        operationId = "updateChat",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Чат обновлен.", content = [Content(schema = Schema(implementation = Chat::class))]),
            ApiResponse(responseCode = "404", description = "Чат не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.PATCH],
        value = ["/chats/{chatId}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateChat(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "") @Valid @RequestBody(required = false) chatUpdate: ChatUpdate?): ResponseEntity<Chat> {
        return ResponseEntity(service.updateChat(chatId, chatUpdate), HttpStatus.valueOf(200))
    }
}
