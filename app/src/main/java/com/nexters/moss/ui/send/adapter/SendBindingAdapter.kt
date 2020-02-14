package com.nexters.moss.ui.send.adapter

import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.model.CakeModel

class SendBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setCakeList")
        fun setCakeList(rv: RecyclerView, list: ArrayList<CakeModel>) {
            with(rv.adapter) {
                if (this is SendAdapter) {
                    setCakeList(list)
                }
            }
        }

        @JvmStatic
        @BindingAdapter("isBtnEnabled")
        fun isBtnEnabled(btn: Button, enabled: Boolean) {
            btn.isEnabled = enabled
        }
    }
}