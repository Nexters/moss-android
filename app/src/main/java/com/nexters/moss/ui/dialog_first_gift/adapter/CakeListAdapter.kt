package com.nexters.moss.ui.dialog_first_gift.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R

class CakeListAdapter : RecyclerView.Adapter<CakeListAdapter.CakeListViewHolder>() {
    private lateinit var context: Context

    private val images = arrayOf(
        R.drawable.icon_category_cake_watermelon,
        R.drawable.icon_category_cake_cheese,
        R.drawable.icon_category_cake_cream,
        R.drawable.icon_category_cake_greentea,
        R.drawable.icon_category_cake_coffee,
        R.drawable.icon_category_cake_apple,
        R.drawable.icon_category_cake_chestnut,
        R.drawable.icon_category_cake_
    )

    private val names = arrayOf(
        "수박케익", "치즈케익", "생크림케익",
        "녹차케익", "커피케익", "사과케익",
        "밤케익", "아몬드케익"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeListViewHolder {
        context = parent.context

        return CakeListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_cake_list_all, parent, false
            )
        )
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: CakeListViewHolder, position: Int) {
        holder.cakeImage.setImageResource(images[position])
        holder.cakeName.text = names[position]
    }


    class CakeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cakeImage: ImageView = itemView.findViewById(R.id.iv_cakeImage)
        val cakeName: TextView = itemView.findViewById(R.id.tv_cakeName)
    }
}