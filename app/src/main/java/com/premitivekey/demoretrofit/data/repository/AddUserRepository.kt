package com.premitivekey.demoretrofit.data.repository

import com.premitivekey.demoretrofit.data.model.CreateUserRequest
import com.premitivekey.demoretrofit.data.model.CreateUserResponse
import com.premitivekey.demoretrofit.network.UserApi
import com.premitivekey.demoretrofit.presentation.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddUserRepository @Inject constructor(private  val api:UserApi) {

    fun createUser(request: CreateUserRequest): Flow<UiState<CreateUserResponse>> = flow {

        emit(UiState.Loading)

        val response = api.createUser(request)

        if (response.isSuccessful && response.body() != null) {
            emit(UiState.Success(response.body()!!))
        } else {
            emit(UiState.Error("Error code: ${response.code()}"))
        }

    }.catch { e ->
        emit(UiState.Error(e.message ?: "Unknown error"))
    }.flowOn(Dispatchers.IO)
}