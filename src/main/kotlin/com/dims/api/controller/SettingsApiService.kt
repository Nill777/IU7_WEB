package com.dims.api.controller

import com.dims.api.model.AppSettings

interface SettingsApiService {

    /**
     * GET /users/{userId}/settings : Получить настройки пользователя
     *
     * @param userId  (required)
     * @return Объект с настройками. (status code 200)
     *         or Доступ запрещен. (status code 403)
     * @see SettingsApi#getAppSettings
     */
    fun getAppSettings(userId: java.util.UUID): AppSettings

    /**
     * PATCH /users/{userId}/settings : Обновить настройки пользователя
     *
     * @param userId  (required)
     * @param appSettings  (optional)
     * @return Настройки обновлены. (status code 200)
     *         or Доступ запрещен. (status code 403)
     * @see SettingsApi#updateAppSettings
     */
    fun updateAppSettings(userId: java.util.UUID, appSettings: AppSettings?): AppSettings
}
