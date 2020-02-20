package com.nexters.moss.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryModel (
    val cakeName : String,
    val dates : List<String>,
    val description : String,
    val habitName : String,
    val imagePath : String
) : Parcelable