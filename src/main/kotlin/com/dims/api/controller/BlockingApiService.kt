package com.dims.api.controller

import com.dims.api.model.Block
import com.dims.api.model.BlockCreate
import com.dims.api.model.PaginatedBlocks

interface BlockingApiService {

    /**
     * POST /users/{userId}/blocks : Заблокировать пользователя от имени userId
     *
     * @param userId  (required)
     * @param blockCreate  (required)
     * @return Пользователь заблокирован. (status code 201)
     *         or Доступ запрещен. (status code 403)
     * @see BlockingApi#blockUser
     */
    fun blockUser(userId: java.util.UUID, blockCreate: BlockCreate): Block

    /**
     * GET /users/{userId}/blocks : Получить список заблокированных пользователей для userId
     *
     * @param userId  (required)
     * @param page Номер страницы (optional, default to 1)
     * @param limit Количество элементов на странице (optional, default to 25)
     * @return Страница со списком блокировок. (status code 200)
     *         or Доступ запрещен. (status code 403)
     * @see BlockingApi#getBlockedUsers
     */
    fun getBlockedUsers(userId: java.util.UUID, page: kotlin.Int, limit: kotlin.Int): PaginatedBlocks

    /**
     * DELETE /users/{userId}/blocks/{blockedUserId} : Разблокировать пользователя от имени userId
     *
     * @param userId  (required)
     * @param blockedUserId  (required)
     * @return Пользователь разблокирован. (status code 204)
     *         or Доступ запрещен. (status code 403)
     *         or Запись о блокировке не найдена. (status code 404)
     * @see BlockingApi#unblockUser
     */
    fun unblockUser(userId: java.util.UUID, blockedUserId: java.util.UUID): Unit
}
