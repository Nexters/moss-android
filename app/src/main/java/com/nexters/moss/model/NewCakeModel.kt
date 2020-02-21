package com.nexters.moss.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewCakeModel (
    val nickname: String,
    val note: String,
    val cakeName: String,
    val imagePath: String
) : Parcelable