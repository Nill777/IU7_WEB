package com.dims.api.controller

import com.dims.api.model.AuthToken
import com.dims.api.model.User
import com.dims.api.model.UserCreate
import com.dims.api.model.UserLogin
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
class AuthenticationApiController(@Autowired(required = true) val service: AuthenticationApiService) {

    @Operation(
        summary = "Аутентификация пользователя",
        operationId = "loginUser",
        description = """""",
        responses = [
            ApiResponse(responseCode = "200", description = "Успешный вход.", content = [Content(schema = Schema(implementation = AuthToken::class))]),
            ApiResponse(responseCode = "401", description = "Ошибка аутентификации.") ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/auth/login"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun loginUser(@Parameter(description = "", required = true) @Valid @RequestBody userLogin: UserLogin): ResponseEntity<AuthToken> {
        return ResponseEntity(service.loginUser(userLogin), HttpStatus.valueOf(200))
    }

    @Operation(
        summary = "Регистрация нового пользователя",
        operationId = "registerUser",
        description = """""",
        responses = [
            ApiResponse(responseCode = "201", description = "Пользователь создан.", content = [Content(schema = Schema(implementation = User::class))]),
            ApiResponse(responseCode = "409", description = "Пользователь с таким именем уже существует.") ]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/auth/register"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun registerUser(@Parameter(description = "", required = true) @Valid @RequestBody userCreate: UserCreate): ResponseEntity<User> {
        return ResponseEntity(service.registerUser(userCreate), HttpStatus.valueOf(201))
    }
}
