package com.dims.api.controller

import com.dims.api.model.Message
import com.dims.api.model.MessageCreate
import com.dims.api.model.MessageHistory
import com.dims.api.model.MessageUpdate
import com.dims.api.model.PaginatedMessages
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
class MessagesApiController(@Autowired(required = true) val service: MessagesApiService) {

    @Operation(
        summary = "Удалить сообщение",
        operationId = "deleteMessage",
        description = """""",
        responses = [
            ApiResponse(responseCode = "204", description = "Сообщение удалено."),
            ApiResponse(responseCode = "403", description = "Доступ запрещен."),
            ApiResponse(responseCode = "404", description = "Сообщение не найдено.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/chats/{chatId}/messages/{messageId}"]
    )
    fun deleteMessage(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "", required = true) @PathVariable("messageId") messageId: java.util.UUID): ResponseEntity<Unit> {
        return ResponseEntity(service.deleteMessage(chatId, messageId), HttpStatus.valueOf(204))
    }

    @Operation(
        summary = "Редактировать сообщение",
        operationId = "editMessage",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Сообщение обновлено.", content = [Content(schema = Schema(implementation = Message::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен."),
            ApiResponse(responseCode = "404", description = "Сообщение не найдено.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.PATCH],
        value = ["/chats/{chatId}/messages/{messageId}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun editMessage(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "", required = true) @PathVariable("messageId") messageId: java.util.UUID,@Parameter(description = "", required = true) @Valid @RequestBody messageUpdate: MessageUpdate): ResponseEntity<Message> {
        return ResponseEntity(service.editMessage(chatId, messageId, messageUpdate), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Получить историю сообщений",
        operationId = "getChatMessages",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Страница со списком сообщений.", content = [Content(schema = Schema(implementation = PaginatedMessages::class))]),
            ApiResponse(responseCode = "404", description = "Чат не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/chats/{chatId}/messages"],
        produces = ["application/json"]
    )
    fun getChatMessages(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "", schema = Schema(defaultValue = "50")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "50") limit: kotlin.Int,@Parameter(description = "Курсор для получения сообщений старше указанной временной метки.") @Valid @RequestParam(value = "before_timestamp", required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) beforeTimestamp: java.time.OffsetDateTime?): ResponseEntity<PaginatedMessages> {
        return ResponseEntity(service.getChatMessages(chatId, limit, beforeTimestamp), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Получить сообщение по ID",
        operationId = "getMessageById",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Данные сообщения.", content = [Content(schema = Schema(implementation = Message::class))]),
            ApiResponse(responseCode = "404", description = "Сообщение не найдено.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/chats/{chatId}/messages/{messageId}"],
        produces = ["application/json"]
    )
    fun getMessageById(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "", required = true) @PathVariable("messageId") messageId: java.util.UUID): ResponseEntity<Message> {
        return ResponseEntity(service.getMessageById(chatId, messageId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Получить историю изменений сообщения",
        operationId = "getMessageHistory",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Список изменений.", content = [Content(array = ArraySchema(schema = Schema(implementation = MessageHistory::class)))]),
            ApiResponse(responseCode = "404", description = "Сообщение не найдено.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/chats/{chatId}/messages/{messageId}/history"],
        produces = ["application/json"]
    )
    fun getMessageHistory(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "", required = true) @PathVariable("messageId") messageId: java.util.UUID): ResponseEntity<List<MessageHistory>> {
        return ResponseEntity(service.getMessageHistory(chatId, messageId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Отправить сообщение",
        operationId = "sendMessage",
        description = """""",
        responses = [
            ApiResponse(responseCode = "201", description = "Сообщение создано.", content = [Content(schema = Schema(implementation = Message::class))]),
            ApiResponse(responseCode = "404", description = "Чат не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/chats/{chatId}/messages"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun sendMessage(@Parameter(description = "", required = true) @PathVariable("chatId") chatId: java.util.UUID,@Parameter(description = "", required = true) @Valid @RequestBody messageCreate: MessageCreate): ResponseEntity<Message> {
        return ResponseEntity(service.sendMessage(chatId, messageCreate), HttpStatus.valueOf(201))
    }
}
