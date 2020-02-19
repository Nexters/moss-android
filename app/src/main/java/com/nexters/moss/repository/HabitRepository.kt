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

    suspend fun deleteHabit(userId: Int, habitId: Int): Any {
        val json = JsonObject()
        json.addProperty("habitId", habitId)

        DLog.d("$userId, $json")
        return habitApi.deleteHabit(userId, json.toString())
    }

    suspend fun getHabit(habikeryToken: String): Any {
        return habitApi.getHabit(habikeryToken)
    }

    suspend fun doneHabit(habitId: Int): Any {
        val json = JsonObject()
        json.addProperty("habitId", habitId)

        DLog.d("$json")
        return habitApi.doneHabit(json.toString())
    }
}