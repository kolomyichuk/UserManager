package com.example.getuserwithretrofitmvi.ui.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getuserwithretrofitmvi.data.model.User
import com.example.getuserwithretrofitmvi.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

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
                val response = repository.getUsers()
                val users = response.users
                _state.value = UserState.Success(users)
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun deleteUser(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteUser(id)
                val currentUsers =
                    (_state.value as UserState.Success)?.users?.toMutableList() ?: mutableListOf()
                currentUsers.removeAll { it.id == id }

                _state.value = UserState.Success(currentUsers)
            } catch (e: Exception) {
                _state.value = UserState.Error("Error: deleting user")
            }
        }
    }


    private fun updateUser(id: Int, user: User) {
        viewModelScope.launch {
            try {
                val updatedUser = repository.updateUser(id, user)

                val currentUsers = (_state.value as? UserState.Success)?.users?.toMutableList()
                    ?: mutableListOf()

                val index = currentUsers.indexOfFirst { it.id == id }
                if (index != -1) {
                    currentUsers[index] = updatedUser
                }

                _state.value = UserState.Success(currentUsers)
            } catch (e: Exception) {
                _state.value = UserState.Error("Error: Updating user $e")
            }
        }
    }
}