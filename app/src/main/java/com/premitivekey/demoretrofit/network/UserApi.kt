package com.premitivekey.demoretrofit.network

import com.premitivekey.demoretrofit.data.model.CreateUserRequest
import com.premitivekey.demoretrofit.data.model.CreateUserResponse
import com.premitivekey.demoretrofit.data.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    @POST("users")
    suspend fun createUser(
        @Body request: CreateUserRequest
    ): Response<CreateUserResponse>
}