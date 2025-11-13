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
 * @param username 
 * @param role 
 */
data class UserCreate(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("username", required = true) val username: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("password", required = true) val password: kotlin.String,

    @Schema(example = "null", description = "")
    @get:JsonProperty("role") val role: UserCreate.Role? = Role.USER
    ) {

    /**
    * 
    * Values: USER,MODERATOR,ADMINISTRATOR
    */
    enum class Role(@get:JsonValue val value: kotlin.String) {

        USER("USER"),
        MODERATOR("MODERATOR"),
        ADMINISTRATOR("ADMINISTRATOR");

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.String): Role {
                return values().firstOrNull{it -> it.value == value}
                    ?: throw IllegalArgumentException("Unexpected value '$value' for enum 'UserCreate'")
            }
        }
    }

}

