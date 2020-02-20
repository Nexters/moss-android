package com.nexters.moss.ui.diary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.moss.R
import com.nexters.moss.model.DiaryModel

class DiaryPieceRecyclerAdapter : RecyclerView.Adapter<DiaryPieceRecyclerAdapter.Holder>() {
    private var context : Context? = null
    private var itemList = ArrayList<DiaryModel>()

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtClear = itemView.findViewById<TextView>(R.id.txt_diary_item_clear)
        private val txtDescription = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_description)
        private val txtCakeName = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_name)
        private val cakeImage = itemView.findViewById<ImageView>(R.id.img_diary_item_cake)
        private val imgCount = itemView.findViewById<ImageView>(R.id.img_diary_item_cake_count)
        private val txtCount = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_count)

        fun bind(item : DiaryModel) {
            txtClear.text = item.habitName
            txtDescription.text = item.description
            txtCakeName.text = item.cakeName
            Glide.with(context!!).load(item.imagePath).into(cakeImage)

            imgCount.visibility = View.GONE
            txtCount.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_diary, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}