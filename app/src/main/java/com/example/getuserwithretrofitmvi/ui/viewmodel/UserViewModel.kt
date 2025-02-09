package com.example.getuserwithretrofitmvi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getuserwithretrofitmvi.data.model.User
import com.example.getuserwithretrofitmvi.data.repository.UserRepository
import com.example.getuserwithretrofitmvi.ui.intent.UserIntent
import com.example.getuserwithretrofitmvi.ui.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(val repository: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow<UserState>(UserState.Loading)
    val state: StateFlow<UserState> = _state.asStateFlow()

    fun dispatch(intent: UserIntent) {
        when (intent) {
            is UserIntent.LoadUsers -> getUsers()
            is UserIntent.DeleteUser -> deleteUser(intent.id)
            is UserIntent.UpdateUser -> updateUser(intent.id, intent.user)
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            _state.value = UserState.Loading
            try {
                val users = repository.getUsers()
                _state.value = UserState.Success(users)
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun deleteUser(id: Int) {
        viewModelScope.launch {
            repository.deleteUser(id)
            getUsers()
        }
    }

    private fun updateUser(id: Int, user: User) {
        viewModelScope.launch {
            repository.updateUser(id, user)
            getUsers()
        }
    }
}