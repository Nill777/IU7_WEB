package com.dims.api.controller

import com.dims.api.model.Chat
import com.dims.api.model.ChatCreate
import com.dims.api.model.ChatUpdate
import com.dims.api.model.PaginatedChats

interface ChatsApiService {

    /**
     * POST /chats : Создать новый чат
     *
     * @param chatCreate  (required)
     * @return Чат создан. (status code 201)
     * @see ChatsApi#createChat
     */
    fun createChat(chatCreate: ChatCreate): Chat

    /**
     * DELETE /chats/{chatId} : Удалить чат
     *
     * @param chatId  (required)
     * @return Чат удален. (status code 204)
     *         or Чат не найден. (status code 404)
     * @see ChatsApi#deleteChat
     */
    fun deleteChat(chatId: java.util.UUID): Unit

    /**
     * GET /chats/{chatId} : Получить чат по ID
     *
     * @param chatId  (required)
     * @return Информация о чате. (status code 200)
     *         or Чат не найден. (status code 404)
     * @see ChatsApi#getChatById
     */
    fun getChatById(chatId: java.util.UUID): Chat

    /**
     * GET /chats : Получить список чатов
     * Возвращает список чатов. Требуется фильтрация по &#x60;userId&#x60;.
     *
     * @param userId Фильтр для получения чатов конкретного пользователя. (required)
     * @param page  (optional, default to 1)
     * @param limit  (optional, default to 25)
     * @return Страница со списком чатов. (status code 200)
     *         or Доступ запрещен. (status code 403)
     * @see ChatsApi#getChats
     */
    fun getChats(userId: java.util.UUID, page: kotlin.Int, limit: kotlin.Int): PaginatedChats

    /**
     * PATCH /chats/{chatId} : Обновить название чата
     *
     * @param chatId  (required)
     * @param chatUpdate  (optional)
     * @return Чат обновлен. (status code 200)
     *         or Чат не найден. (status code 404)
     * @see ChatsApi#updateChat
     */
    fun updateChat(chatId: java.util.UUID, chatUpdate: ChatUpdate?): Chat
}
