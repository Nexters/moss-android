package com.nexters.moss.constant

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.nexters.moss.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class HabitListConstant(
    private val habitName: String,
    private val cakeName: String,
    @ColorRes private val personalColor: Int,
    @ColorRes private val secondaryColor: Int,
    @DrawableRes private val imageRes: Int,
    @DrawableRes private val imageResBg: Int
) : Parcelable {
    DRINK_WATER(
        "물마시기",
        "수박케익",
        R.color.strawberry,
        R.color.pale_coral,
        R.drawable.icon_category_cake_watermelon,
        R.drawable.icon_cake_watermelon_bg_secondary
    ),
    STRETCHING(
        "스트레칭",
        "치즈케익",
        R.color.orange,
        R.color.pale_orange,
        R.drawable.icon_category_cake_cheese,
        R.drawable.icon_cake_cheese_bg_secondary
    ),
    MEDITATION(
        "명상",
        "크림케익",
        R.color.blue,
        R.color.pale_blue,
        R.drawable.icon_category_cake_cream,
        R.drawable.icon_cake_cream_bg_secondary
    ),
    WALK(
        "산책",
        "녹차케익",
        R.color.green,
        R.color.pale_green,
        R.drawable.icon_category_cake_greentea,
        R.drawable.icon_cake_greentea_bg_secondary
    ),
    WATCH_NEWS(
        "뉴스보기",
        "커피케익",
        R.color.brown,
        R.color.pale_brown,
        R.drawable.icon_category_cake_coffee,
        R.drawable.icon_cake_coffee_bg_secondary
    ),
    HAVE_BREAKFAST(
        "아침식사",
        "딸기케익",
        R.color.strawberry,
        R.color.pale_coral,
        R.drawable.icon_category_cake_apple,
        R.drawable.icon_cake_apple_bg_secondary
    ),
    WRITE_DIARY(
        "일기쓰기",
        "밤케익",
        R.color.blue,
        R.color.pale_blue,
        R.drawable.icon_category_cake_chestnut,
        R.drawable.icon_cake_chestnut_bg_secondary
    ),
    READ_BOOK(
        "책읽기",
        "아몬드케익",
        R.color.orange,
        R.color.pale_orange,
        R.drawable.icon_category_cake_,
        R.drawable.icon_cake_amond_bg_secondary
    );

    fun getHabitName() = habitName
    fun getCakeName() = cakeName

    @DrawableRes
    fun getDrawableRes() = imageRes

    @ColorRes
    fun getSecondaryColor() = secondaryColor

    @DrawableRes
    fun getBgImage() = imageResBg

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

        @JvmStatic
        fun getCategoryIdByName(name: String): Int {
            var i = 0
            for (item in values().indices) {
                if (values()[item].cakeName == name) {
                    i = item
                }
            }

            return i
        }
    }
}