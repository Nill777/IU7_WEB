package com.dims.api.controller

import com.dims.api.model.PaginatedUsers
import com.dims.api.model.User
import com.dims.api.model.UserUpdate
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class UsersApiTest {

    private val service: UsersApiService = UsersApiServiceImpl()
    private val api: UsersApiController = UsersApiController(service)

    /**
     * To test UsersApiController.deleteUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun deleteUserTest() {
        val userId: java.util.UUID = TODO()
        val response: ResponseEntity<Unit> = api.deleteUser(userId)

        // TODO: test validations
    }

    /**
     * To test UsersApiController.getAllUsers
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getAllUsersTest() {
        val username: kotlin.String? = TODO()
        val page: kotlin.Int = TODO()
        val limit: kotlin.Int = TODO()
        val response: ResponseEntity<PaginatedUsers> = api.getAllUsers(username, page, limit)

        // TODO: test validations
    }

    /**
     * To test UsersApiController.getUserById
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun getUserByIdTest() {
        val userId: java.util.UUID = TODO()
        val response: ResponseEntity<User> = api.getUserById(userId)

        // TODO: test validations
    }

    /**
     * To test UsersApiController.updateUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun updateUserTest() {
        val userId: java.util.UUID = TODO()
        val userUpdate: UserUpdate? = TODO()
        val response: ResponseEntity<User> = api.updateUser(userId, userUpdate)

        // TODO: test validations
    }
}
