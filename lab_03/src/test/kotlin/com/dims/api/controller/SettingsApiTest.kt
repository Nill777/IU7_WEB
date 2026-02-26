package com.dims.api.controller

import com.dims.api.model.AppSettings
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class SettingsApiTest {

    private val service: SettingsApiService = SettingsApiServiceImpl()
    private val api: SettingsApiController = SettingsApiController(service)

    /**
     * To test SettingsApiController.getAppSettings
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getAppSettingsTest() {
        val userId: java.util.UUID = TODO()
        val response: ResponseEntity<AppSettings> = api.getAppSettings(userId)

        // TODO: test validations
    }

    /**
     * To test SettingsApiController.updateAppSettings
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun updateAppSettingsTest() {
        val userId: java.util.UUID = TODO()
        val appSettings: AppSettings? = TODO()
        val response: ResponseEntity<AppSettings> = api.updateAppSettings(userId, appSettings)

        // TODO: test validations
    }
}
