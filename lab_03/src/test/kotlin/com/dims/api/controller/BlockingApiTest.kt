package com.dims.api.controller

import com.dims.api.model.Block
import com.dims.api.model.BlockCreate
import com.dims.api.model.PaginatedBlocks
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class BlockingApiTest {

    private val service: BlockingApiService = BlockingApiServiceImpl()
    private val api: BlockingApiController = BlockingApiController(service)

    /**
     * To test BlockingApiController.blockUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun blockUserTest() {
        val userId: java.util.UUID = TODO()
        val blockCreate: BlockCreate = TODO()
        val response: ResponseEntity<Block> = api.blockUser(userId, blockCreate)

        // TODO: test validations
    }

    /**
     * To test BlockingApiController.getBlockedUsers
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getBlockedUsersTest() {
        val userId: java.util.UUID = TODO()
        val page: kotlin.Int = TODO()
        val limit: kotlin.Int = TODO()
        val response: ResponseEntity<PaginatedBlocks> = api.getBlockedUsers(userId, page, limit)

        // TODO: test validations
    }

    /**
     * To test BlockingApiController.unblockUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun unblockUserTest() {
        val userId: java.util.UUID = TODO()
        val blockedUserId: java.util.UUID = TODO()
        val response: ResponseEntity<Unit> = api.unblockUser(userId, blockedUserId)

        // TODO: test validations
    }
}
