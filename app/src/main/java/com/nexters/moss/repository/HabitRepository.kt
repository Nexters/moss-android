package com.nexters.moss.repository

import com.google.gson.JsonObject
import com.nexters.moss.api.HabitApi
import com.nexters.moss.utils.DLog

class HabitRepository(private val habitApi: HabitApi) {
    suspend fun createHabit(userId: Int, categoryId: Int): Any {
        val json = JsonObject()
        json.addProperty("categoryId", categoryId)

        DLog.d("$userId, $json")
        return habitApi.createHabit(userId, json.toString())
    }
}