package com.example.getuserwithretrofitmvi.data.network

import com.example.getuserwithretrofitmvi.data.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)

    @POST("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): User
}