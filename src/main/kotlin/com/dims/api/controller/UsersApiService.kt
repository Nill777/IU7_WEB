package com.dims.api.controller

import com.dims.api.model.PaginatedUsers
import com.dims.api.model.User
import com.dims.api.model.UserUpdate

interface UsersApiService {

    /**
     * DELETE /users/{userId} : Удалить пользователя по ID (Admin)
     *
     * @param userId  (required)
     * @return Пользователь удален. (status code 204)
     *         or Доступ запрещен. (status code 403)
     *         or Пользователь не найден. (status code 404)
     * @see UsersApi#deleteUser
     */
    fun deleteUser(userId: java.util.UUID): Unit

    /**
     * GET /users : Получить список всех пользователей (Admin)
     *
     * @param username  (optional)
     * @param page Номер страницы (optional, default to 1)
     * @param limit Количество элементов на странице (optional, default to 25)
     * @return Страница со списком пользователей. (status code 200)
     *         or Доступ запрещен. (status code 403)
     * @see UsersApi#getAllUsers
     */
    fun getAllUsers(username: kotlin.String?, page: kotlin.Int, limit: kotlin.Int): PaginatedUsers

    /**
     * GET /users/{userId} : Получить пользователя по ID
     *
     * @param userId  (required)
     * @return Профиль пользователя. (status code 200)
     *         or Пользователь не найден. (status code 404)
     * @see UsersApi#getUserById
     */
    fun getUserById(userId: java.util.UUID): User

    /**
     * PATCH /users/{userId} : Обновить пользователя по ID
     *
     * @param userId  (required)
     * @param userUpdate  (optional)
     * @return Пользователь обновлен. (status code 200)
     *         or Доступ запрещен. (status code 403)
     *         or Пользователь не найден. (status code 404)
     * @see UsersApi#updateUser
     */
    fun updateUser(userId: java.util.UUID, userUpdate: UserUpdate?): User
}
