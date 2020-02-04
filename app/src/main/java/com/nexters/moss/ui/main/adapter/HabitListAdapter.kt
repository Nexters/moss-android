package com.nexters.moss.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import java.util.*
import kotlin.collections.ArrayList

class HabitListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HabitItemTouchHelper.OnItemTouchListener {

    private var itemList = ArrayList<String>()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            VIEW_TYPE_HABIT -> {
                HabitViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_main_habit, parent, false)
                )
            }
            else -> {
                AddHabitViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_main_add_habit, parent, false)
                )
            }
        }
    }

    override fun getItemCount() = itemList.size + 1
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddHabitViewHolder -> {
                holder.itemView.setOnClickListener {
                    Toast.makeText(context, "추가", Toast.LENGTH_SHORT).show()
                }
            }
            is HabitViewHolder -> {
                holder.habitName.text = itemList[position]
            }
            is EditHabitViewHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList.size == position) {
            VIEW_TYPE_ADD_HABIT
        } else {
            VIEW_TYPE_HABIT
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (toPosition != itemList.size) {
            Collections.swap(itemList, fromPosition, toPosition)
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    fun refreshItemList(list: ArrayList<String>) {
        itemList = list
    }

    class AddHabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.tv_habitName)
    }

    class EditHabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    companion object {
        const val VIEW_TYPE_ADD_HABIT = 0
        const val VIEW_TYPE_HABIT = 1
        const val VIEW_TYPE_EDIT_HABIT = 2
    }

}