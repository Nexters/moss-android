package com.nexters.moss.ui.formation_habit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.moss.R

class FormationHabitListAdapter : RecyclerView.Adapter<FormationHabitListAdapter.FormationHabitListViewHolder>() {
    private var itemList = ArrayList<String>()
    private lateinit var context: Context
    private var lastSelectedView: ImageView? = null
    private var onItemClickListener: OnItemClickListener? = null

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

        holder.itemView.setOnClickListener {
            if (lastSelectedView != null) {
                if (lastSelectedView == holder.cakeImage)
                    return@setOnClickListener

                Glide.with(context)
                    .load(context.getDrawable(R.drawable.icon_category_cake_greentea))
                    .into(lastSelectedView!!)
            }

            Glide.with(context)
                .load(context.getDrawable(R.drawable.icon_category_cake_greentea_selected))
                .into(holder.cakeImage)
            lastSelectedView = holder.cakeImage
            onItemClickListener?.onItemClick(itemList[position])
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