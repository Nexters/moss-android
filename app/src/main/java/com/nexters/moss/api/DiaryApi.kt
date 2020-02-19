package com.nexters.moss.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DiaryApi {
    companion object {
        private const val baseUrl = "/api/diary"
    }

    @GET("$baseUrl/history")
    suspend fun getCakeHistory(@Header("categoryId") categoryId: Int,
                               @Query("habikeryToken") habikeryToken: String): Any

    @GET("$baseUrl/piece")
    suspend fun getPieceOfCakeDiary(@Header("habikeryToken") habikeryToken: String): Any

    @GET("$baseUrl/whole")
    suspend fun getWholeCakeDiary(@Header("habikeryToken") habikeryToken: String): Any
}