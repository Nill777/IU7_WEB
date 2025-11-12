package com.dims.api.model

import java.util.Objects
import com.dims.api.model.Message
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
 * Страница с сообщениями, использующая курсорную пагинацию.
 * @param items 
 * @param nextCursor Timestamp последнего сообщения для запроса следующей страницы.
 * @param hasNextPage 
 */
data class PaginatedMessages(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("items") val items: kotlin.collections.List<Message>? = null,

    @Schema(example = "null", description = "Timestamp последнего сообщения для запроса следующей страницы.")
    @get:JsonProperty("nextCursor") val nextCursor: java.time.OffsetDateTime? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("hasNextPage") val hasNextPage: kotlin.Boolean? = null
    ) {

}

