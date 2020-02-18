package com.nexters.moss.ui.formation_habit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.constant.HabitListConstant

class FormationHabitListAdapter : RecyclerView.Adapter<FormationHabitListAdapter.FormationHabitListViewHolder>() {
    private var itemList = ArrayList<String>()
    private lateinit var context: Context
    private var lastSelectedView: ImageView? = null
    private var onItemClickListener: OnItemClickListener? = null

    private var lastSelectedText: View? = null
    private val itemViews = ArrayList<TextView>()

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
        if (itemViews.size < itemList.size) {
            itemViews.add(holder.habitName)
        }

        val item = itemList[position]

        holder.habitName.text = item
        holder.cakeImage.setImageResource(HabitListConstant.values()[position].getDrawableRes())

        var color: Int

        if (lastSelectedView != null) {
            color = if (lastSelectedView == holder.cakeImage) {
                context.resources.getColor(HabitListConstant.getPersonalColor(item), null)
            } else {
                context.resources.getColor(R.color.textColorSmall, null)
            }
            holder.habitName.setTextColor(color)
        }

        holder.itemView.setOnClickListener {
            if (lastSelectedView != null) {
                if (lastSelectedView == holder.cakeImage)
                    return@setOnClickListener

                lastSelectedView?.alpha = 0.2f
            }

            holder.cakeImage.alpha = 1f
            lastSelectedView = holder.cakeImage
            lastSelectedText = holder.habitName
            onItemClickListener?.onItemClick(itemList[position])

            for (itemView in itemViews) {
                color = if (itemView == lastSelectedText) {
                    context.resources.getColor(HabitListConstant.getPersonalColor(item), null)
                }
                else {
                    context.resources.getColor(R.color.uiColorDisable, null)
                }
                itemView.setTextColor(color)
            }
        }
    }

    fun refreshItemList(list: ArrayList<String>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(item: String) {
                listener(item)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: String)
    }

    class FormationHabitListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.tv_habitName)
        val cakeImage: ImageView = itemView.findViewById(R.id.iv_cake)
    }
}