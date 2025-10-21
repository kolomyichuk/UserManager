package com.example.getuserwithretrofitmvi.data.network

import com.example.getuserwithretrofitmvi.data.network.model.User
import com.example.getuserwithretrofitmvi.data.network.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsers(): UserResponse

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)

    @PATCH("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): User
}
