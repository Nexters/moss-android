package com.nexters.moss.model.response

import com.google.gson.annotations.SerializedName
import com.nexters.moss.model.HabitModel
import com.nexters.moss.model.NewCakeModel

data class DoneHabitResponseModel (
    @SerializedName("data")
    val habitModel: HabitModel,
    val newCakeDTO: NewCakeModel
)