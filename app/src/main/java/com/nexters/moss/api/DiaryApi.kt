package com.nexters.moss.api

import com.nexters.moss.model.response.HistoryResponseModel
import com.nexters.moss.model.response.DiaryListResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DiaryApi {
    companion object {
        private const val baseUrl = "/api/diary"
    }

    @GET("$baseUrl/history")
    suspend fun getCakeHistory(@Header("habikeryToken") habikeryToken: String,
                               @Query("categoryId") categoryId: Int): HistoryResponseModel

    @GET("$baseUrl/piece")
    suspend fun getPieceOfCakeDiary(@Header("habikeryToken") habikeryToken: String): DiaryListResponseModel

    @GET("$baseUrl/whole")
    suspend fun getWholeCakeDiary(@Header("habikeryToken") habikeryToken: String): DiaryListResponseModel
}