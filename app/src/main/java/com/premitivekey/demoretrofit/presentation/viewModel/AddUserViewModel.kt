package com.premitivekey.demoretrofit.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.premitivekey.demoretrofit.data.model.CreateUserRequest
import com.premitivekey.demoretrofit.data.model.CreateUserResponse
import com.premitivekey.demoretrofit.data.repository.AddUserRepository
import com.premitivekey.demoretrofit.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class AddUserViewModel @Inject constructor(private val repository: AddUserRepository): ViewModel(){

    private val _createUserState =
        MutableStateFlow<UiState<CreateUserResponse>>(UiState.Idle)

    val createUserState: StateFlow<UiState<CreateUserResponse>> = _createUserState

    fun createUser(name: String, email: String) {

        viewModelScope.launch {

            val request = CreateUserRequest(name, email)

            repository.createUser(request).collect {

                _createUserState.value = it
            }
        }
    }
}