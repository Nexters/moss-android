package com.nexters.moss.extension

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.nexters.moss.constant.SharedPreferenceConstant

fun Activity.getUserSharedPreference(): SharedPreferences {
    return getSharedPreferences(
        SharedPreferenceConstant.USER_PREFERENCE_NAME.getValue(),
        MODE_PRIVATE
    )
}