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
 * Метаданные для постраничной навигации.
 * @param totalItems 
 * @param totalPages 
 * @param currentPage 
 * @param limit 
 */
data class PaginationInfo(

    @Schema(example = "null", description = "")
    @get:JsonProperty("totalItems") val totalItems: kotlin.Int? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("totalPages") val totalPages: kotlin.Int? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("currentPage") val currentPage: kotlin.Int? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("limit") val limit: kotlin.Int? = null
    ) {

}

