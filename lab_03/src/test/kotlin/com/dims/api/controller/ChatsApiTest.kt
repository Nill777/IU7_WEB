package com.dims.api.controller

import com.dims.api.model.Chat
import com.dims.api.model.ChatCreate
import com.dims.api.model.ChatUpdate
import com.dims.api.model.PaginatedChats
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class ChatsApiTest {

    private val service: ChatsApiService = ChatsApiServiceImpl()
    private val api: ChatsApiController = ChatsApiController(service)

    /**
     * To test ChatsApiController.createChat
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun createChatTest() {
        val chatCreate: ChatCreate = TODO()
        val response: ResponseEntity<Chat> = api.createChat(chatCreate)

        // TODO: test validations
    }

    /**
     * To test ChatsApiController.deleteChat
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteChatTest() {
        val chatId: java.util.UUID = TODO()
        val response: ResponseEntity<Unit> = api.deleteChat(chatId)

        // TODO: test validations
    }

    /**
     * To test ChatsApiController.getChatById
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getChatByIdTest() {
        val chatId: java.util.UUID = TODO()
        val response: ResponseEntity<Chat> = api.getChatById(chatId)

        // TODO: test validations
    }

    /**
     * To test ChatsApiController.getChats
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getChatsTest() {
        val userId: java.util.UUID = TODO()
        val page: kotlin.Int = TODO()
        val limit: kotlin.Int = TODO()
        val response: ResponseEntity<PaginatedChats> = api.getChats(userId, page, limit)

        // TODO: test validations
    }

    /**
     * To test ChatsApiController.updateChat
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun updateChatTest() {
        val chatId: java.util.UUID = TODO()
        val chatUpdate: ChatUpdate? = TODO()
        val response: ResponseEntity<Chat> = api.updateChat(chatId, chatUpdate)

        // TODO: test validations
    }
}
