package com.nexters.moss.ui.send.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.model.CakeModel

class SendBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("setCakeList")
        fun setCakeList(rv: RecyclerView, list: ArrayList<CakeModel>) {
            with (rv.adapter) {
                if (this is SendAdapter) {
                    setCakeList(list)
                }
            }
        }
    }
}