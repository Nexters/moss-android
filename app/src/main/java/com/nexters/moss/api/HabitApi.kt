package com.nexters.moss.api

import com.nexters.moss.model.request.CategoryRequestModel
import com.nexters.moss.model.response.HabitListResponseModel
import retrofit2.http.*

interface HabitApi {
    companion object {
        private const val baseUrl = "/api/habit"
    }

    @POST(baseUrl)
    suspend fun createHabit(@Header("habikeryToken") habikeryToken: String, @Body categoryRequestModel: CategoryRequestModel): Any

    @DELETE("$baseUrl/{userId}")
    suspend fun deleteHabit(@Path("userId") userId: Int, @Body habitId: String): Any

    @GET(baseUrl)
    suspend fun getHabit(@Header("habikeryToken") habikeryToken: String): HabitListResponseModel

    @GET("$baseUrl/record")
    suspend fun doneHabit(@Body habitId: String): Any

}