package com.nexters.moss.model

data class DiaryModel(
    val cakeName: String,
    val dates: List<String>,
    val description: String,
    val habitName: String,
    val imagePath: String,
    val count : Int
)
