package com.nexters.moss.api

import com.nexters.moss.model.request.CategoryRequestModel
import com.nexters.moss.model.request.HabitOrderRequestModel
import com.nexters.moss.model.response.DoneHabitResponseModel
import com.nexters.moss.model.response.HabitListResponseModel
import retrofit2.http.*

interface HabitApi {
    companion object {
        private const val baseUrl = "/api/habit"
    }

    @POST(baseUrl)
    suspend fun createHabit(@Header("habikeryToken") habikeryToken: String, @Body categoryRequestModel: CategoryRequestModel): Any

    @HTTP(method = "DELETE", path = baseUrl, hasBody = true)
    suspend fun deleteHabit(@Header("habikeryToken") habikeryToken: String, @Body habitId: Int): Any

    @GET(baseUrl)
    suspend fun getHabit(@Header("habikeryToken") habikeryToken: String): HabitListResponseModel

    @PUT(baseUrl)
    suspend fun doneHabit(@Header("habikeryToken") habikeryToken: String, @Body habitId: Int): DoneHabitResponseModel

    @PUT("$baseUrl/order")
    suspend fun changeOrderHabit(@Header("habikeryToken") habikeryToken: String, @Body habitOrderRequestModel: HabitOrderRequestModel): Any
}