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
 * @param id 
 * @param blockerId 
 * @param blockedUserId 
 * @param timestamp 
 * @param reason 
 */
data class Block(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("id", required = true) val id: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("blockerId", required = true) val blockerId: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("blockedUserId", required = true) val blockedUserId: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("timestamp", required = true) val timestamp: java.time.OffsetDateTime,

    @Schema(example = "null", description = "")
    @get:JsonProperty("reason") val reason: kotlin.String? = null
    ) {

}

