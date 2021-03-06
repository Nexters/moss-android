package com.nexters.moss.model

data class HabitModel(
    val habitId: Int,
    val categoryId: Int,
    val firstCheck: Boolean,
    val name: String,
    val records: List<HabitCheckModel>
)