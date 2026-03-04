package com.premitivekey.demoretrofit.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.premitivekey.demoretrofit.data.model.UserDto
import com.premitivekey.demoretrofit.data.repository.UserRepository
import com.premitivekey.demoretrofit.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _usersState = MutableStateFlow<UiState<List<UserDto>>>(UiState.Loading)
    val usersState: StateFlow<UiState<List<UserDto>>> = _usersState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            repository.getUsers().collect {
                _usersState.value = it
            }
        }
    }
}