package com.dims.api.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param accessToken 
 * @param userId 
 */
data class AuthToken(

    @Schema(example = "null", description = "")
    @get:JsonProperty("accessToken") val accessToken: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("userId") val userId: java.util.UUID? = null
    ) {

}

