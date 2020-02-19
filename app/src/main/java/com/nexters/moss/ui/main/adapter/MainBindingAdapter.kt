package com.nexters.moss.ui.main.adapter

import android.view.Gravity
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView

class MainBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("isDrawerOpen")
        fun isDrawerOpen(dl: DrawerLayout, opened: Boolean) {
            if (opened) {
                dl.openDrawer(Gravity.LEFT)
            } else {
                dl.closeDrawer(Gravity.LEFT)
            }
        }

        @JvmStatic
        @BindingAdapter("setItemList")
        fun setItemList(rv: RecyclerView, itemList: ArrayList<String>) {
            val adapter = rv.adapter
            with(adapter) {
                if (this is HabitListAdapter) {
                    refreshItemList(itemList)
                }
            }

        }
    }
}