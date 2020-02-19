package com.nexters.moss.api

import com.nexters.moss.model.response.GetUserInfoResponseModel
import com.nexters.moss.model.response.LoginResponseModel
import retrofit2.http.*

interface UserApi {
    @POST("/api/user")
    suspend fun join(
        @Header("accessToken") accessToken: String,
        @Body nickname: String
    ): Any

    @POST("/api/token")
    suspend fun login(
        @Header("accessToken") accessToken: String
    ): LoginResponseModel

    @DELETE("/api/user")
    suspend fun leave(
        @Header("habikeryToken") accessToken: String
    ): Any

    @GET("/api/user")
    suspend fun getUserInfo(
        @Header("habikeryToken") accessToken: String
    ): GetUserInfoResponseModel
}