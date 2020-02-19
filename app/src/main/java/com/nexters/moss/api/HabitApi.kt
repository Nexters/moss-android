package com.nexters.moss.api

import retrofit2.http.*

interface HabitApi {
    companion object {
        private const val baseUrl = "/api/habit"
    }

    @POST("$baseUrl/{userId}")
    suspend fun createHabit(@Path("userId") userId: Int, @Body categoryId: String): Any

    @DELETE("$baseUrl/{userId}")
    suspend fun deleteHabit(@Path("userId") userId: Int, @Body habitId: String): Any

    @GET(baseUrl)
    suspend fun getHabit(@Header("habikeryToken") habikeryToken: String): Any

    @GET("$baseUrl/record")
    suspend fun doneHabit(@Body habitId: String): Any
}