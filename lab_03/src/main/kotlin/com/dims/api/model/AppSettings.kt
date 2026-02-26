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
 * @param THEME 0: System, 1: Light, 2: Dark
 * @param MAX_FILE_SIZE 
 * @param MESSAGE_ENCRYPTION 
 * @param HISTORY_STORAGE 
 */
data class AppSettings(

    @Schema(example = "null", description = "0: System, 1: Light, 2: Dark")
    @get:JsonProperty("THEME") val theme: AppSettings.THEME? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("MAX_FILE_SIZE") val maxFileSize: AppSettings.MAXFILESIZE? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("MESSAGE_ENCRYPTION") val messageEncryption: AppSettings.MESSAGEENCRYPTION? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("HISTORY_STORAGE") val historyStorage: AppSettings.HISTORYSTORAGE? = null
    ) {

    /**
    * 0: System, 1: Light, 2: Dark
    * Values: _0,_1,_2
    */
    enum class THEME(@get:JsonValue val value: kotlin.Int) {

        _0(0),
        _1(1),
        _2(2);

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.Int): THEME {
                return values().firstOrNull{it -> it.value == value}
                    ?: throw IllegalArgumentException("Unexpected value '$value' for enum 'AppSettings'")
            }
        }
    }

    /**
    * 
    * Values: _10,_50,_100
    */
    enum class MAXFILESIZE(@get:JsonValue val value: kotlin.Int) {

        _10(10),
        _50(50),
        _100(100);

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.Int): MAXFILESIZE {
                return values().firstOrNull{it -> it.value == value}
                    ?: throw IllegalArgumentException("Unexpected value '$value' for enum 'AppSettings'")
            }
        }
    }

    /**
    * 
    * Values: _0,_1
    */
    enum class MESSAGEENCRYPTION(@get:JsonValue val value: kotlin.Int) {

        _0(0),
        _1(1);

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.Int): MESSAGEENCRYPTION {
                return values().firstOrNull{it -> it.value == value}
                    ?: throw IllegalArgumentException("Unexpected value '$value' for enum 'AppSettings'")
            }
        }
    }

    /**
    * 
    * Values: _7,_30,_365
    */
    enum class HISTORYSTORAGE(@get:JsonValue val value: kotlin.Int) {

        _7(7),
        _30(30),
        _365(365);

        companion object {
            @JvmStatic
            @JsonCreator
            fun forValue(value: kotlin.Int): HISTORYSTORAGE {
                return values().firstOrNull{it -> it.value == value}
                    ?: throw IllegalArgumentException("Unexpected value '$value' for enum 'AppSettings'")
            }
        }
    }

}

