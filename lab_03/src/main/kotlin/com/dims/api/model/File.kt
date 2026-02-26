package com.dims.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param id 
 * @param name 
 * @param path 
 * @param uploadedBy 
 * @param uploadedTimestamp 
 * @param type 
 */
data class File(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("id", required = true) val id: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("name", required = true) val name: kotlin.String,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("path", required = true) val path: java.net.URI,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("uploadedBy", required = true) val uploadedBy: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("uploadedTimestamp", required = true) val uploadedTimestamp: java.time.OffsetDateTime,

    @Schema(example = "null", description = "")
    @get:JsonProperty("type") val type: kotlin.String? = null
    ) {

}

