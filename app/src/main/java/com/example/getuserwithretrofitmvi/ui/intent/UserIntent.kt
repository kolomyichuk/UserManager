package com.example.getuserwithretrofitmvi.ui.intent

import com.example.getuserwithretrofitmvi.data.model.User

sealed class UserIntent {

    data object LoadUsers : UserIntent()

    data class DeleteUser(val id: Int) : UserIntent()

    data class UpdateUser(val id: Int, val user: User) : UserIntent()
}