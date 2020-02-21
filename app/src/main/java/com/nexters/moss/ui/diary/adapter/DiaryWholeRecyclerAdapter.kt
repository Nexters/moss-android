package com.nexters.moss.ui.diary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.moss.R
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.model.DiaryModel
import com.nexters.moss.ui.diary.DiaryWholeFragment
import com.nexters.moss.ui.diary_history.DiaryHistoryDialog

class DiaryWholeRecyclerAdapter(val itemList:ArrayList<DiaryModel>) : RecyclerView.Adapter<DiaryWholeRecyclerAdapter.Holder>() {
    private var context : Context? = null
    private var onClick: OnItemClickListener? = null

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtClear = itemView.findViewById<TextView>(R.id.txt_diary_item_clear)
        private val txtDescription =
            itemView.findViewById<TextView>(R.id.txt_diary_item_cake_description)
        private val txtCakeName = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_name)
        private val cakeImage = itemView.findViewById<ImageView>(R.id.img_diary_item_cake)
        private val txtCount = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_count)

        fun bind(item: DiaryModel) {
            txtClear.text = item.habitName
            txtDescription.text = item.description
            txtCakeName.text = item.cakeName + "케익"
            txtCount.text = item.count.toString()
            Glide.with(context!!).asBitmap().load("http://" + item.imagePath).into(cakeImage)
        }

        fun clickEvent(){
            itemView.setOnClickListener {
                onClick?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_diary, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(itemList[position])
        holder.clickEvent()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onClick = object : OnItemClickListener {
            override fun onItemClick(item: Int) {
                listener(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Int)
    }

    fun refreshItemList(list: ArrayList<DiaryModel>) {
        with(itemList as ArrayList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

}