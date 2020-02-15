package com.nexters.moss.api

import retrofit2.http.*

interface HabitApi {
    @POST("/api/habit/{userId}")
    suspend fun createHabit(@Path("userId") userId: Int, @Body categoryId: String): Any
}