package com.dims.api.controller

import com.dims.api.model.Message
import com.dims.api.model.MessageCreate
import com.dims.api.model.MessageHistory
import com.dims.api.model.MessageUpdate
import com.dims.api.model.PaginatedMessages
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class MessagesApiTest {

    private val service: MessagesApiService = MessagesApiServiceImpl()
    private val api: MessagesApiController = MessagesApiController(service)

    /**
     * To test MessagesApiController.deleteMessage
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteMessageTest() {
        val chatId: java.util.UUID = TODO()
        val messageId: java.util.UUID = TODO()
        val response: ResponseEntity<Unit> = api.deleteMessage(chatId, messageId)

        // TODO: test validations
    }

    /**
     * To test MessagesApiController.editMessage
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun editMessageTest() {
        val chatId: java.util.UUID = TODO()
        val messageId: java.util.UUID = TODO()
        val messageUpdate: MessageUpdate = TODO()
        val response: ResponseEntity<Message> = api.editMessage(chatId, messageId, messageUpdate)

        // TODO: test validations
    }

    /**
     * To test MessagesApiController.getChatMessages
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getChatMessagesTest() {
        val chatId: java.util.UUID = TODO()
        val limit: kotlin.Int = TODO()
        val beforeTimestamp: java.time.OffsetDateTime? = TODO()
        val response: ResponseEntity<PaginatedMessages> = api.getChatMessages(chatId, limit, beforeTimestamp)

        // TODO: test validations
    }

    /**
     * To test MessagesApiController.getMessageById
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getMessageByIdTest() {
        val chatId: java.util.UUID = TODO()
        val messageId: java.util.UUID = TODO()
        val response: ResponseEntity<Message> = api.getMessageById(chatId, messageId)

        // TODO: test validations
    }

    /**
     * To test MessagesApiController.getMessageHistory
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getMessageHistoryTest() {
        val chatId: java.util.UUID = TODO()
        val messageId: java.util.UUID = TODO()
        val response: ResponseEntity<List<MessageHistory>> = api.getMessageHistory(chatId, messageId)

        // TODO: test validations
    }

    /**
     * To test MessagesApiController.sendMessage
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun sendMessageTest() {
        val chatId: java.util.UUID = TODO()
        val messageCreate: MessageCreate = TODO()
        val response: ResponseEntity<Message> = api.sendMessage(chatId, messageCreate)

        // TODO: test validations
    }
}
