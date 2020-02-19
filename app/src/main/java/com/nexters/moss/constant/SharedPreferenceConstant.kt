package com.nexters.moss.constant

enum class SharedPreferenceConstant(private val value: String) {
    USER_PREFERENCE_NAME("user_preference_name"),
    ACCESS_TOKEN("access_token"),
    HABIKERY_TOKEN("habikery_token"),
    UNKNOWN("unknown");

    fun getValue() = value
}