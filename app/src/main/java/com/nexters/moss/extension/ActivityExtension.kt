package com.nexters.moss.extension

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.nexters.moss.R
import com.nexters.moss.constant.SharedPreferenceConstant

fun Activity.getUserSharedPreference(): SharedPreferences {
    return getSharedPreferences(
        SharedPreferenceConstant.USER_PREFERENCE_NAME.getValue(),
        MODE_PRIVATE
    )
}

fun Activity.showHabikeryToast(content: String) {
    val toastTopValue = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        21f,
        this.resources.displayMetrics
    ).toInt()


    Toast(this).apply {
        view = layoutInflater.inflate(R.layout.layout_habikery_toast, null)
        view.findViewById<TextView>(R.id.tv_toastMessage).text = content
        duration = Toast.LENGTH_SHORT
        setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, toastTopValue)
        show()
    }
}