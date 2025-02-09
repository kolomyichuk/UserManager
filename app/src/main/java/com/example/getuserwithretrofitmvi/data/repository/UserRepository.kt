package com.example.getuserwithretrofitmvi.data.repository

import com.example.getuserwithretrofitmvi.data.model.User
import com.example.getuserwithretrofitmvi.data.model.UserResponse
import com.example.getuserwithretrofitmvi.data.network.UserApi

class UserRepository(private val api: UserApi) {

    suspend fun getUsers(): UserResponse = api.getUsers()

    suspend fun deleteUser(id: Int) = api.deleteUser(id)

    suspend fun updateUser(id: Int, user: User) = api.updateUser(id, user)
}