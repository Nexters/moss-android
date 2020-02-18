package com.nexters.moss.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("/api/user")
    suspend fun join(
        @Header("accessToken") accessToken: String,
        @Body nickname: String
    ): Any

    @DELETE("/api/user")
    suspend fun leave(
        @Header("habikeryToken") accessToken: String
    ): Any
}