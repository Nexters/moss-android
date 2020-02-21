package com.nexters.moss.repository

import com.nexters.moss.api.DiaryApi
import com.nexters.moss.model.response.HistoryResponseModel
import com.nexters.moss.model.response.DiaryListResponseModel

class DiaryRepository(private val diaryApi: DiaryApi) {

    suspend fun getCakeHistory(habikeryToken: String, categoryId: Int) : HistoryResponseModel {
        return diaryApi.getCakeHistory(habikeryToken, categoryId)
    }

    suspend fun getPieceOfCakeDiary(habikeryToken: String): DiaryListResponseModel {
        return diaryApi.getPieceOfCakeDiary(habikeryToken)
    }

    suspend fun getWholeCakeDiary(habikeryToken: String): DiaryListResponseModel {
        return diaryApi.getWholeCakeDiary(habikeryToken)
    }


}