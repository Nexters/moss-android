package com.nexters.moss.repository

import com.nexters.moss.api.UserApi
import com.nexters.moss.model.response.GetUserInfoResponseModel
import com.nexters.moss.model.response.LoginResponseModel

class UserRepository(private val userApi: UserApi) {
    suspend fun join(accessToken: String, nickname: String): Any {
        return userApi.join(accessToken, nickname)
    }

    suspend fun login(accessToken: String): LoginResponseModel {
        return userApi.login(accessToken)
    }

    suspend fun leave(habikeryToken: String): Any {
        return userApi.leave(habikeryToken)
    }

    suspend fun getUserInfo(habikeryToken: String): GetUserInfoResponseModel {
        return userApi.getUserInfo(habikeryToken)
    }
}