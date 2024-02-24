package com.felpster.userslist.data.api

import com.felpster.userslist.data.model.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}