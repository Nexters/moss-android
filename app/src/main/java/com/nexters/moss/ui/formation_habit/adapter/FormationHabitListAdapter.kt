package com.nexters.moss.ui.formation_habit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R

class FormationHabitListAdapter : RecyclerView.Adapter<FormationHabitListAdapter.FormationHabitListViewHolder>() {
    private var itemList = ArrayList<String>()
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FormationHabitListViewHolder {
        context = parent.context

        return FormationHabitListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_formation_habit, parent, false)
        )
    }
    override fun getItemCount() = itemList.size
    override fun onBindViewHolder(holder: FormationHabitListViewHolder, position: Int) {
        val item = itemList[position]

        holder.habitName.text = item
    }

    fun refreshItemList(list: ArrayList<String>) {
        itemList = list
        notifyDataSetChanged()
    }

    class FormationHabitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.tv_habitName)
        val cakeImage: ImageView = itemView.findViewById(R.id.iv_cake)
    }
}