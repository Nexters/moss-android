package com.nexters.moss.ui.main.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class HabitItemTouchHelper : ItemTouchHelper.Callback() {
    private var onItemTouchListener: OnItemTouchListener? = null

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN

        return makeMovementFlags(dragFlag, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        onItemTouchListener?.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit

    fun attachItemTouchAdapter(adapter: OnItemTouchListener) {
        onItemTouchListener = adapter
    }

    interface OnItemTouchListener {
        fun onItemMove(fromPosition: Int, toPosition: Int)
    }
}