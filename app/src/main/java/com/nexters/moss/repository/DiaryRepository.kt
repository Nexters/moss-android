package com.nexters.moss.repository

import com.nexters.moss.api.DiaryApi
import com.nexters.moss.model.response.DiaryListResponseModel

class DiaryRepository(private val diaryApi: DiaryApi) : Any() {
    suspend fun getCakeHistory(categoryId: Int, habikeryToken: String) {
        return diaryApi.getCakeHistory(categoryId, habikeryToken) as Unit
    }

    suspend fun getPieceOfCakeDiary(habikeryToken: String): DiaryListResponseModel {
        return diaryApi.getPieceOfCakeDiary(habikeryToken)
    }

    suspend fun getWholeCakeDiary(habikeryToken: String): DiaryListResponseModel {
        return diaryApi.getWholeCakeDiary(habikeryToken)
    }


}