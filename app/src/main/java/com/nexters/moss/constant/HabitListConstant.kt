package com.nexters.moss.constant

import androidx.annotation.DrawableRes
import com.nexters.moss.R

enum class HabitListConstant(
    private val habitName: String,
    @DrawableRes private val imageRes: Int
) {
    DRINK_WATER("물마시기", R.drawable.icon_category_cake_watermelon),
    STRETCHING("스트레칭", R.drawable.icon_category_cake_cheese),
    MEDITATION("명상", R.drawable.icon_category_cake_cream),
    WALK("산책", R.drawable.icon_category_cake_greentea),
    WATCH_NEWS("뉴스보기", R.drawable.icon_category_cake_coffee),
    HAVE_BREAKFAST("아침식사", R.drawable.icon_category_cake_apple),
    WRITE_DIARY("일기쓰기", R.drawable.icon_category_cake_chestnut),
    READ_BOOK("책읽기", R.drawable.icon_category_cake_);

    fun getValue() = habitName

    @DrawableRes
    fun getDrawableRes() = imageRes

}