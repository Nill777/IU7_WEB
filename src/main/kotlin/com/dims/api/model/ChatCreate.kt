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
 * @param companionId 
 * @param name Необязательное имя чата.
 * @param isGroupChat 
 */
data class ChatCreate(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("companionId", required = true) val companionId: java.util.UUID,

    @Schema(example = "null", description = "Необязательное имя чата.")
    @get:JsonProperty("name") val name: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("isGroupChat") val isGroupChat: kotlin.Boolean? = false
    ) {

}

