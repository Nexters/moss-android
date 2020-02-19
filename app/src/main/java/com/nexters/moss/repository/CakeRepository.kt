package com.nexters.moss.repository

import com.google.gson.JsonObject
import com.nexters.moss.api.CakeApi
import com.nexters.moss.model.request.CreateNewCakeRequestModel

class CakeRepository(private val cakeApi: CakeApi) {
    suspend fun sendCake(habikeryToken: String, categoryId: Int, note: String): Any {
        val json = JsonObject()
        json.addProperty("categoryId", categoryId)
        json.addProperty("note", note)

        val request = CreateNewCakeRequestModel(categoryId, note)

        return cakeApi.sendCake(habikeryToken, request)
    }
}