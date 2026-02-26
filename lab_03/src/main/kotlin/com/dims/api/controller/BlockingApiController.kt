package com.dims.api.controller

import com.dims.api.model.Block
import com.dims.api.model.BlockCreate
import com.dims.api.model.PaginatedBlocks
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
class BlockingApiController(@Autowired(required = true) val service: BlockingApiService) {

    @Operation(
        summary = "Заблокировать пользователя от имени userId",
        operationId = "blockUser",
        description = """""",
        responses = [
            ApiResponse(responseCode = "201", description = "Пользователь заблокирован.", content = [Content(schema = Schema(implementation = Block::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/users/{userId}/blocks"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun blockUser(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID,@Parameter(description = "", required = true) @Valid @RequestBody blockCreate: BlockCreate): ResponseEntity<Block> {
        return ResponseEntity(service.blockUser(userId, blockCreate), HttpStatus.valueOf(201))
    }

    @Operation(
        summary = "Получить список заблокированных пользователей для userId",
        operationId = "getBlockedUsers",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Страница со списком блокировок.", content = [Content(schema = Schema(implementation = PaginatedBlocks::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{userId}/blocks"],
        produces = ["application/json"]
    )
    fun getBlockedUsers(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID,@Parameter(description = "Номер страницы", schema = Schema(defaultValue = "1")) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") page: kotlin.Int,@Parameter(description = "Количество элементов на странице", schema = Schema(defaultValue = "25")) @Valid @RequestParam(value = "limit", required = false, defaultValue = "25") limit: kotlin.Int): ResponseEntity<PaginatedBlocks> {
        return ResponseEntity(service.getBlockedUsers(userId, page, limit), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Разблокировать пользователя от имени userId",
        operationId = "unblockUser",
        description = """""",
        responses = [
            ApiResponse(responseCode = "204", description = "Пользователь разблокирован."),
            ApiResponse(responseCode = "403", description = "Доступ запрещен."),
            ApiResponse(responseCode = "404", description = "Запись о блокировке не найдена.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/users/{userId}/blocks/{blockedUserId}"]
    )
    fun unblockUser(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID,@Parameter(description = "", required = true) @PathVariable("blockedUserId") blockedUserId: java.util.UUID): ResponseEntity<Unit> {
        return ResponseEntity(service.unblockUser(userId, blockedUserId), HttpStatus.valueOf(204))
    }
}
