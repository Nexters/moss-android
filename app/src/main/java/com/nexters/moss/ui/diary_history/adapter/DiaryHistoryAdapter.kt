package com.nexters.moss.ui.diary_history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import kotlinx.android.synthetic.main.item_history.view.*

class DiaryHistoryAdapter(val dateList: List<String>) :
    RecyclerView.Adapter<DiaryHistoryAdapter.Holder>() {
    private var context: Context? = null

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(date: String) {
            val num = dateList.size - adapterPosition

            itemView.txt_history_num.text = "${num}판"
            itemView.txt_history_date.text = date.slice(IntRange(0, 9))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dateList[position])
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

}
