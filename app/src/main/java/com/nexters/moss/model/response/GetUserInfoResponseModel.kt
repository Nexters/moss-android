package com.nexters.moss.model.response

import com.google.gson.annotations.SerializedName

data class GetUserInfoResponseModel(
    @SerializedName("data")
    val nickname: String?
)