package com.example.getuserwithretrofitmvi.ui.screens.users

import com.example.getuserwithretrofitmvi.data.model.User

sealed class UserState {
    data object Loading : UserState()

    data class Success(val users: List<User>) : UserState()

    data class Error(val message: String) : UserState()
}