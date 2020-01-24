package com.nexters.moss.ui.send

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R

class SendAdapter (private val context: Context, private val cakeList:ArrayList<CakeModel>) :
    RecyclerView.Adapter<SendAdapter.Holder>() {
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val habit = itemView.findViewById<TextView>(R.id.txt_send_recycler_habit)

        fun bind(unit : CakeModel) {
            habit?.text = unit.habit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_send_item, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(cakeList[position])
    }

    override fun getItemCount(): Int {
        return cakeList.size
    }




}