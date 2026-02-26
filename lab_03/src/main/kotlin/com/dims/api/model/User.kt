package com.dims.api.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
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
 * @param username 
 * @param role 
 * @param profileSettingsId 
 * @param appSettingsId 
 */
data class User(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("id", required = true) val id: java.util.UUID,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("username", required = true) val username: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("role", required = true) val role: User.Role,

    @Schema(example = "null", description = "")
    @get:JsonProperty("profileSettingsId") val profileSettingsId: java.util.UUID? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("appSettingsId") val appSettingsId: java.util.UUID? = null
    ) {

    /**
    * 
    * Values: UNAUTHORIZED_USER,USER,MODERATOR,ADMINISTRATOR
    */
    enum class Role(@get:JsonValue val value: kotlin.String) {

        UNAUTHORIZED_USER("UNAUTHORIZED_USER"),
        USER("USER"),
        MODERATOR("MODERATOR"),
        ADMINISTRATOR("ADMINISTRATOR");

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.String): Role {
                return values().firstOrNull{it -> it.value == value}
                    ?: throw IllegalArgumentException("Unexpected value '$value' for enum 'User'")
            }
        }
    }

}

