package com.dims.api.model

import java.util.Objects
import com.dims.api.model.Block
import com.dims.api.model.PaginationInfo
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
 * @param items 
 * @param pagination 
 */
data class PaginatedBlocks(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("items") val items: kotlin.collections.List<Block>? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("pagination") val pagination: PaginationInfo? = null
    ) {

}

