package com.dims.api.controller

import com.dims.api.model.File
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
class FilesApiController(@Autowired(required = true) val service: FilesApiService) {

    @Operation(
        summary = "Удалить файл",
        operationId = "deleteFile",
        description = """""",
        responses = [
            ApiResponse(responseCode = "204", description = "Файл удален."),
            ApiResponse(responseCode = "404", description = "Файл не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/files/{fileId}"]
    )
    fun deleteFile(@Parameter(description = "", required = true) @PathVariable("fileId") fileId: java.util.UUID): ResponseEntity<Unit> {
        return ResponseEntity(service.deleteFile(fileId), HttpStatus.valueOf(204))
    }

    @Operation(
        summary = "Скачать файл по ID",
        operationId = "downloadFile",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Содержимое файла.", content = [Content(schema = Schema(implementation = org.springframework.core.io.Resource::class))]),
            ApiResponse(responseCode = "404", description = "Файл не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/files/{fileId}/download"],
        produces = ["application/octet-stream"]
    )
    fun downloadFile(@Parameter(description = "", required = true) @PathVariable("fileId") fileId: java.util.UUID): ResponseEntity<org.springframework.core.io.Resource> {
        return ResponseEntity(service.downloadFile(fileId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Получить метаданные файла",
        operationId = "getFileMetadata",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Метаданные файла.", content = [Content(schema = Schema(implementation = File::class))]),
            ApiResponse(responseCode = "404", description = "Файл не найден.") ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/files/{fileId}"],
        produces = ["application/json"]
    )
    fun getFileMetadata(@Parameter(description = "", required = true) @PathVariable("fileId") fileId: java.util.UUID): ResponseEntity<File> {
        return ResponseEntity(service.getFileMetadata(fileId), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Загрузить новый файл",
        operationId = "uploadFile",
        description = """""",
        responses = [
            ApiResponse(responseCode = "201", description = "Файл успешно загружен.", content = [Content(schema = Schema(implementation = File::class))]) ],
        security = [ SecurityRequirement(name = "bearerAuth") ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/files"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun uploadFile(@Parameter(description = "") @Valid @RequestPart("file", required = false) file: org.springframework.web.multipart.MultipartFile?): ResponseEntity<File> {
        return ResponseEntity(service.uploadFile(file), HttpStatus.valueOf(201))
    }
}
