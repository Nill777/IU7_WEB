package com.dims.api.controller

import com.dims.api.model.AuthToken
import com.dims.api.model.User
import com.dims.api.model.UserCreate
import com.dims.api.model.UserLogin

interface AuthenticationApiService {

    /**
     * POST /auth/login : Аутентификация пользователя
     *
     * @param userLogin  (required)
     * @return Успешный вход. (status code 200)
     *         or Ошибка аутентификации. (status code 401)
     * @see AuthenticationApi#loginUser
     */
    fun loginUser(userLogin: UserLogin): AuthToken

    /**
     * POST /auth/register : Регистрация нового пользователя
     *
     * @param userCreate  (required)
     * @return Пользователь создан. (status code 201)
     *         or Пользователь с таким именем уже существует. (status code 409)
     * @see AuthenticationApi#registerUser
     */
    fun registerUser(userCreate: UserCreate): User
}
