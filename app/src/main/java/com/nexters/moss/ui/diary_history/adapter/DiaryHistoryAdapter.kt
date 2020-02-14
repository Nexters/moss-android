package com.nexters.moss.ui.diary_history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R

class DiaryHistoryAdapter(private val dateList: ArrayList<String>) :
    RecyclerView.Adapter<DiaryHistoryAdapter.Holder>(){
    private var context: Context? = null

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNum = itemView.findViewById<TextView>(R.id.txt_history_num)
        private val txtDate = itemView.findViewById<TextView>(R.id.txt_history_date)

        fun bind(date: String) {
            val num = dateList.size - adapterPosition

            txtNum?.text = "${num}판"
            txtDate?.text = date
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
