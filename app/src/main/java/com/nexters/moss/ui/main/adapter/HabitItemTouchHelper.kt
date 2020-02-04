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
        val position = viewHolder.adapterPosition

        return if (position == recyclerView.childCount - 1) {
            makeMovementFlags(0, 0)
        }
        else {
            makeMovementFlags(dragFlag, 0)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        if (from == recyclerView.adapter!!.itemCount - 1)
            return false

        onItemTouchListener?.onItemMove(from, to)
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