package com.dims.api.controller

import com.dims.api.model.AppSettings
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
class SettingsApiController(@Autowired(required = true) val service: SettingsApiService) {

    @Operation(
        summary = "Получить настройки пользователя",
        operationId = "getAppSettings",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Объект с настройками.", content = [Content(schema = Schema(implementation = AppSettings::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{userId}/settings"],
        produces = ["application/json"]
    )
    fun getAppSettings(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID): ResponseEntity<AppSettings> {
        return ResponseEntity(service.getAppSettings(userId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Обновить настройки пользователя",
        operationId = "updateAppSettings",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Настройки обновлены.", content = [Content(schema = Schema(implementation = AppSettings::class))]),
            ApiResponse(responseCode = "403", description = "Доступ запрещен.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.PATCH],
        value = ["/users/{userId}/settings"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateAppSettings(@Parameter(description = "", required = true) @PathVariable("userId") userId: java.util.UUID,@Parameter(description = "") @Valid @RequestBody(required = false) appSettings: AppSettings?): ResponseEntity<AppSettings> {
        return ResponseEntity(service.updateAppSettings(userId, appSettings), HttpStatus.valueOf(200))
    }
}
