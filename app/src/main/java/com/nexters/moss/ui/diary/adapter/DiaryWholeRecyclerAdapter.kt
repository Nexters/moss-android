package com.nexters.moss.ui.diary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.ui.diary.DiaryWholeFragment
import com.nexters.moss.ui.diary_history.DiaryHistoryDialog

class DiaryWholeRecyclerAdapter(val cakeList:ArrayList<DiaryCakeModel>) : RecyclerView.Adapter<DiaryWholeRecyclerAdapter.Holder>() {
    private var context : Context? = null
    private var onClick: OnItemClickListener? = null

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtClear = itemView.findViewById<TextView>(R.id.txt_diary_item_clear)
        private val txtSubjective = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_description)
        private val txtCakeName = itemView.findViewById<TextView>(R.id.txt_diary_item_cake_name)

        fun bind(item : DiaryCakeModel) {
            txtClear?.text = item.habitClear
            txtSubjective?.text = item.cakeSubjective
            txtCakeName?.text = item.cakeName
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
        holder.bind(cakeList[position])
        holder.clickEvent()
    }

    override fun getItemCount(): Int {
        return cakeList.size
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


}