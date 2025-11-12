package com.dims.api.controller

import com.dims.api.model.Message
import com.dims.api.model.MessageCreate
import com.dims.api.model.MessageHistory
import com.dims.api.model.MessageUpdate
import com.dims.api.model.PaginatedMessages

interface MessagesApiService {

    /**
     * DELETE /chats/{chatId}/messages/{messageId} : Удалить сообщение
     *
     * @param chatId  (required)
     * @param messageId  (required)
     * @return Сообщение удалено. (status code 204)
     *         or Доступ запрещен. (status code 403)
     *         or Сообщение не найдено. (status code 404)
     * @see MessagesApi#deleteMessage
     */
    fun deleteMessage(chatId: java.util.UUID, messageId: java.util.UUID): Unit

    /**
     * PATCH /chats/{chatId}/messages/{messageId} : Редактировать сообщение
     *
     * @param chatId  (required)
     * @param messageId  (required)
     * @param messageUpdate  (required)
     * @return Сообщение обновлено. (status code 200)
     *         or Доступ запрещен. (status code 403)
     *         or Сообщение не найдено. (status code 404)
     * @see MessagesApi#editMessage
     */
    fun editMessage(chatId: java.util.UUID, messageId: java.util.UUID, messageUpdate: MessageUpdate): Message

    /**
     * GET /chats/{chatId}/messages : Получить историю сообщений
     *
     * @param chatId  (required)
     * @param limit  (optional, default to 50)
     * @param beforeTimestamp Курсор для получения сообщений старше указанной временной метки. (optional)
     * @return Страница со списком сообщений. (status code 200)
     *         or Чат не найден. (status code 404)
     * @see MessagesApi#getChatMessages
     */
    fun getChatMessages(chatId: java.util.UUID, limit: kotlin.Int, beforeTimestamp: java.time.OffsetDateTime?): PaginatedMessages

    /**
     * GET /chats/{chatId}/messages/{messageId} : Получить сообщение по ID
     *
     * @param chatId  (required)
     * @param messageId  (required)
     * @return Данные сообщения. (status code 200)
     *         or Сообщение не найдено. (status code 404)
     * @see MessagesApi#getMessageById
     */
    fun getMessageById(chatId: java.util.UUID, messageId: java.util.UUID): Message

    /**
     * GET /chats/{chatId}/messages/{messageId}/history : Получить историю изменений сообщения
     *
     * @param chatId  (required)
     * @param messageId  (required)
     * @return Список изменений. (status code 200)
     *         or Сообщение не найдено. (status code 404)
     * @see MessagesApi#getMessageHistory
     */
    fun getMessageHistory(chatId: java.util.UUID, messageId: java.util.UUID): List<MessageHistory>

    /**
     * POST /chats/{chatId}/messages : Отправить сообщение
     *
     * @param chatId  (required)
     * @param messageCreate  (required)
     * @return Сообщение создано. (status code 201)
     *         or Чат не найден. (status code 404)
     * @see MessagesApi#sendMessage
     */
    fun sendMessage(chatId: java.util.UUID, messageCreate: MessageCreate): Message
}
