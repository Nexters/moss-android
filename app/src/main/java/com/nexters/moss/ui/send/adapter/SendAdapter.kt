package com.nexters.moss.ui.send.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.model.CakeModel

class SendAdapter :
    RecyclerView.Adapter<SendAdapter.Holder>() {
    private var context: Context? = null
    private var cakeList = ArrayList<CakeModel>()
    private var lastView: View? = null


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val habit = itemView.findViewById<TextView>(R.id.txt_send_recycler_habit)
        private var itemBackground = itemView.findViewById<View>(R.id.layout_send_cake_choice)

        fun bind(unit: CakeModel) {
            habit?.text = unit.habit
        }

        fun changeSelectedBackground() {
            itemView.setOnClickListener {
                Toast.makeText(context, " " + adapterPosition, Toast.LENGTH_SHORT).show()

                if (lastView != null) {
                    lastView!!.setBackgroundResource(R.drawable.send_item_default)
                }

                itemBackground.setBackgroundResource(R.drawable.send_item_selected)
                lastView = itemBackground

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_send_select, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(cakeList[position])
        holder.changeSelectedBackground()
    }

    override fun getItemCount(): Int {
        return cakeList.size
    }

    fun setCakeList(list: ArrayList<CakeModel>) {
        cakeList = list
        notifyDataSetChanged()
    }

}