package com.nexters.moss.api

import com.nexters.moss.model.request.CreateNewCakeRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CakeApi {

    @POST("/api/cake")
    suspend fun sendCake(
        @Header("habikeryToken") habikeryToken: String,
        @Body createNewCakeRequest: CreateNewCakeRequestModel
    ): Any
}