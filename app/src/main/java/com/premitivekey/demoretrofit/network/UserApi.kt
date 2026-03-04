package com.premitivekey.demoretrofit.network

import com.premitivekey.demoretrofit.data.model.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>
}