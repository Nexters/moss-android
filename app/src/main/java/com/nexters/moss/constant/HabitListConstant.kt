package com.nexters.moss.constant

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.nexters.moss.R

enum class HabitListConstant(
    private val habitName: String,
    @ColorRes private val personalColor: Int,
    @DrawableRes private val imageRes: Int
) {
    DRINK_WATER(
        "물마시기",
        R.color.strawberry,
        R.drawable.icon_category_cake_watermelon
    ),
    STRETCHING(
        "스트레칭",
        R.color.orange,
        R.drawable.icon_category_cake_cheese
    ),
    MEDITATION(
        "명상",
        R.color.blue,
        R.drawable.icon_category_cake_cream
    ),
    WALK(
        "산책",
        R.color.green,
        R.drawable.icon_category_cake_greentea
    ),
    WATCH_NEWS(
        "뉴스보기",
        R.color.brown,
        R.drawable.icon_category_cake_coffee
    ),
    HAVE_BREAKFAST(
        "아침식사",
        R.color.strawberry,
        R.drawable.icon_category_cake_apple
    ),
    WRITE_DIARY(
        "일기쓰기",
        R.color.blue,
        R.drawable.icon_category_cake_chestnut
    ),
    READ_BOOK(
        "책읽기",
        R.color.orange,
        R.drawable.icon_category_cake_
    );

    fun getValue() = habitName

    @DrawableRes
    fun getDrawableRes() = imageRes

    companion object {
        @JvmStatic
        @ColorRes
        fun getPersonalColor(name: String): Int {
            var colorRes = R.color.strawberry


            for (habit in values()) {
                if (habit.habitName == name) {
                    colorRes = habit.personalColor
                }
            }

            return colorRes
        }
    }

}