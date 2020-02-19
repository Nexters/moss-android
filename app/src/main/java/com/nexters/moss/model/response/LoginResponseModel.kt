package com.nexters.moss.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("data")
    val habikeryToken: String?
)