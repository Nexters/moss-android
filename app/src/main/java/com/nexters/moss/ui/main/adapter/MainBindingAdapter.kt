package com.nexters.moss.ui.main.adapter

import android.view.Gravity
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout

class MainBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("isDrawerOpen")
        fun isDrawerOpen(dl: DrawerLayout, opened: Boolean) {
            if (opened) {
                dl.openDrawer(Gravity.LEFT)
            }
            else {
                dl.closeDrawer(Gravity.LEFT)
            }
        }
    }
}