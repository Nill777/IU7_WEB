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
 * @param name 
 * @param creatorId 
 * @param isGroupChat 
 * @param companionId 
 */
data class Chat(

    @Schema(example = "null", description = "")
    @get:JsonProperty("id") val id: java.util.UUID? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("name") val name: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("creatorId") val creatorId: java.util.UUID? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("isGroupChat") val isGroupChat: kotlin.Boolean? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("companionId") val companionId: java.util.UUID? = null
    ) {

}

