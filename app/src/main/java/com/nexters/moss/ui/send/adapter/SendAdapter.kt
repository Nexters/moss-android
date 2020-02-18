package com.nexters.moss.ui.send.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.model.CakeModel

class SendAdapter :
    RecyclerView.Adapter<SendAdapter.Holder>() {
    private var context: Context? = null
    private var cakeList = ArrayList<CakeModel>()
    private var lastView: View? = null
    private var firstView: View? = null

    private var onClick: OnItemClickListener? = null
    private var firstCake: OnSetFirstItemListener? = null

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val habit = itemView.findViewById<TextView>(R.id.txt_send_recycler_habit)
        private val image = itemView.findViewById<ImageView>(R.id.img_cake_image)
        private val itemBackground = itemView.findViewById<View>(R.id.layout_send_cake_choice)

        fun bind(unit: CakeModel, position: Int) {
            habit?.text = unit.habit
            image?.setImageResource(unit.image!!)

            if (position == 0) {
                firstView = itemBackground
                firstView?.setBackgroundResource(R.drawable.send_item_selected_coral)
                firstCake?.onSetFirstItem()
            }
        }

        fun changeSelectedBackground() {
            itemView.setOnClickListener {

                if (lastView == null) {
                    firstView!!.setBackgroundResource(R.drawable.send_item_default)
                    habit?.setTypeface(null, Typeface.NORMAL)

                } else {
                    lastView!!.setBackgroundResource(R.drawable.send_item_default)
                    habit?.setTypeface(null, Typeface.NORMAL)
                }

                val itemBackColor = chooseColor(adapterPosition)
                itemBackground.setBackgroundResource(itemBackColor)
                habit.setTypeface(null, Typeface.BOLD)

                //Typeface typeFace = Typeface.createFromAsset(getAssets(), "저장되어있는폰트파일"));
                //Typeface.createFromAsset()

                lastView = itemBackground

                onClick?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_send_select, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(cakeList[position], position)
        holder.changeSelectedBackground()
    }

    override fun getItemCount(): Int {
        return cakeList.size
    }

    fun setCakeList(list: ArrayList<CakeModel>) {
        cakeList = list
        notifyDataSetChanged()
    }

    fun chooseColor(position: Int): Int {
        when (position) {
            0, 5 -> return R.drawable.send_item_selected_coral
            1, 7 -> return R.drawable.send_item_selected_orange
            2, 6 -> return R.drawable.send_item_selected_blue
            3 -> return R.drawable.send_item_selected_green
            4 -> return R.drawable.send_item_selected_brown
        }
        return 0
    }


    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onClick = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener(position)
            }
        }
    }

    fun setOnFirstItemListener(listener: () -> Unit) {
        firstCake = object : OnSetFirstItemListener {
            override fun onSetFirstItem() {
                listener()
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnSetFirstItemListener {
        fun onSetFirstItem()
    }

}