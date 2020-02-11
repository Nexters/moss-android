package com.nexters.moss.ui.dialog_first_gift.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R

class CakeListAdapter : RecyclerView.Adapter<CakeListAdapter.CakeListViewHolder>() {
    private var itemList = ArrayList<String>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeListViewHolder {
        context = parent.context

        return CakeListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_cake_list_all, parent, false
            )
        )
    }

    override fun getItemCount() = 9

    override fun onBindViewHolder(holder: CakeListViewHolder, position: Int) {
    }


    class CakeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}