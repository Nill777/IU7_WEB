package com.dims.api.controller

import com.dims.api.model.AuthToken
import com.dims.api.model.User
import com.dims.api.model.UserCreate
import com.dims.api.model.UserLogin
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class AuthenticationApiTest {

    private val service: AuthenticationApiService = AuthenticationApiServiceImpl()
    private val api: AuthenticationApiController = AuthenticationApiController(service)

    /**
     * To test AuthenticationApiController.loginUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun loginUserTest() {
        val userLogin: UserLogin = TODO()
        val response: ResponseEntity<AuthToken> = api.loginUser(userLogin)

        // TODO: test validations
    }

    /**
     * To test AuthenticationApiController.registerUser
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun registerUserTest() {
        val userCreate: UserCreate = TODO()
        val response: ResponseEntity<User> = api.registerUser(userCreate)

        // TODO: test validations
    }
}
