package com.nexters.moss.repository

import com.nexters.moss.api.UserApi

class UserRepository(private val userApi: UserApi) {
    suspend fun join(accessToken: String, nickname: String): Any {
        return userApi.join(accessToken, nickname)
    }
}