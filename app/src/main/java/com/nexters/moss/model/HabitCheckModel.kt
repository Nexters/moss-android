package com.nexters.moss.model

data class HabitCheckModel(
    val id: Int,
    val createdAt: String,
    val date: String,
    val habitStatus: String,
    val updatedAt: String
)