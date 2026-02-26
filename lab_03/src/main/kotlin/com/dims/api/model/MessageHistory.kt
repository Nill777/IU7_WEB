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
 * @param historyId 
 * @param messageId 
 * @param editedContent 
 * @param editTimestamp 
 */
data class MessageHistory(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("historyId", required = true) val historyId: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("messageId", required = true) val messageId: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("editedContent", required = true) val editedContent: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("editTimestamp", required = true) val editTimestamp: java.time.OffsetDateTime
    ) {

}

