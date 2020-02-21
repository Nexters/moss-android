package com.nexters.moss.repository

import com.google.gson.JsonObject
import com.nexters.moss.api.HabitApi
import com.nexters.moss.model.request.CategoryRequestModel
import com.nexters.moss.model.response.DoneHabitResponseModel
import com.nexters.moss.model.response.HabitListResponseModel
import com.nexters.moss.utils.DLog

class HabitRepository(private val habitApi: HabitApi) {
    suspend fun createHabit(habikeryToken: String, categoryId: Int): Any {
        val request = CategoryRequestModel(categoryId)
        return habitApi.createHabit(habikeryToken, request)
    }

    suspend fun deleteHabit(habikeryToken: String, habitId: Int): Any {

        return habitApi.deleteHabit(habikeryToken, habitId)
    }

    suspend fun getHabit(habikeryToken: String): HabitListResponseModel {
        return habitApi.getHabit(habikeryToken)
    }

    suspend fun doneHabit(habikeryToken: String, habitId: Int): DoneHabitResponseModel {
        return habitApi.doneHabit(habikeryToken, habitId)
    }
}