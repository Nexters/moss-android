package com.nexters.moss.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss.constant.CheckIconColorConstant
import com.nexters.moss.constant.CheckIconConstant
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.ui.formation_habit.FormationHabitActivity
import java.util.*
import kotlin.collections.ArrayList

class HabitListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HabitItemTouchHelper.OnItemTouchListener {

    private var itemList = ArrayList<String>()
    private lateinit var context: Context
    private var isEditMode = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            VIEW_TYPE_HABIT -> {
                HabitViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_main_habit, parent, false)
                )
            }
            VIEW_TYPE_EDIT_HABIT -> {
                EditHabitViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_main_edit_habit,
                        parent,
                        false
                    )
                )
            }
            else -> {
                AddHabitViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_main_add_habit,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isEditMode)
            itemList.size
        else
            itemList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AddHabitViewHolder -> {
                holder.itemView.setOnClickListener {
                    context.startActivity(Intent(context, FormationHabitActivity::class.java))
                }
            }
            is HabitViewHolder -> {
                val item = itemList[position]
                holder.habitName.text = item
                val color =
                    context.resources.getColor(HabitListConstant.getPersonalColor(item), null)
                holder.habitName.setTextColor(color)

                val stateList = arrayOf(
                    CheckIconConstant.CHECKED,
                    CheckIconConstant.CHECKED_CAKE,
                    CheckIconConstant.CHECKED,
                    CheckIconConstant.UNCHECKED_CAKE,
                    CheckIconConstant.UNCHECKED
                )

                var habit = HabitListConstant.DRINK_WATER
                for (h in HabitListConstant.values()) {
                    if (h.getHabitName() == item) {
                        habit = h
                    }
                }
                var isToday: Boolean
                for (i in holder.iconArr.indices) {
                    isToday = false
                    if (i == 1)
                        isToday = true
                    holder.iconArr[i].setImageResource(getCheckIcon(stateList[i], habit, isToday))
                }


            }
            is EditHabitViewHolder -> {
                val item = itemList[position]
                holder.habitName.text = item
                val color =
                    context.resources.getColor(HabitListConstant.getPersonalColor(item), null)
                holder.habitName.setTextColor(color)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList.size == position) {
            VIEW_TYPE_ADD_HABIT
        } else if (isEditMode) {
            VIEW_TYPE_EDIT_HABIT
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

    fun setEditMode(enabled: Boolean) {
        isEditMode = enabled
        notifyDataSetChanged()
    }

    fun getEditMode() = isEditMode

    @DrawableRes
    private fun getCheckIcon(icon: CheckIconConstant, habit: HabitListConstant, isToday: Boolean): Int {
        val colorConstants = CheckIconColorConstant.values()
        val habitConstants = HabitListConstant.values()

        var color = colorConstants[0]

        if (isToday){
            for (i in habitConstants.indices) {
                if (habitConstants[i] == habit) {
                    color = when {
                        i <= 4 -> colorConstants[i]
                        i == 5 -> colorConstants[0]
                        i == 6 -> colorConstants[2]
                        else -> colorConstants[1]
                    }
                    break
                }
            }
        }
        else {
            color = CheckIconColorConstant.DISABLED
        }


        return when (icon) {
            CheckIconConstant.CHECKED -> {
                getCheckedColorIcon(color)
            }
            CheckIconConstant.UNCHECKED -> {
                getUnCheckedColorIcon(color)
            }
            CheckIconConstant.CHECKED_CAKE -> {
                getCheckedCakeColorIcon(color)
            }
            CheckIconConstant.UNCHECKED_CAKE -> {
                getUnCheckedCakeColorIcon(color)
            }
        }
    }

    @DrawableRes
    private fun getCheckedColorIcon(color: CheckIconColorConstant): Int {
        return when (color) {
            CheckIconColorConstant.DISABLED -> {
                R.drawable.icon_disable_checked
            }
            CheckIconColorConstant.STRAWBERRY -> {
                R.drawable.icon_strawberry_checked
            }
            CheckIconColorConstant.ORANGE -> {
                R.drawable.icon_orange_checked
            }
            CheckIconColorConstant.BLUE -> {
                R.drawable.icon_blue_checked
            }
            CheckIconColorConstant.GREEN -> {
                R.drawable.icon_green_checked
            }
            CheckIconColorConstant.BROWN -> {
                R.drawable.icon_brown_checked
            }
        }
    }

    @DrawableRes
    private fun getUnCheckedColorIcon(color: CheckIconColorConstant): Int {
        return when (color) {
            CheckIconColorConstant.DISABLED -> {
                R.drawable.icon_disable_unchecked
            }
            CheckIconColorConstant.STRAWBERRY -> {
                R.drawable.icon_strawberry_unchecked
            }
            CheckIconColorConstant.ORANGE -> {
                R.drawable.icon_orange_unchecked
            }
            CheckIconColorConstant.BLUE -> {
                R.drawable.icon_blue_unchecked
            }
            CheckIconColorConstant.GREEN -> {
                R.drawable.icon_green_unchecked
            }
            CheckIconColorConstant.BROWN -> {
                R.drawable.icon_brown_unchecked
            }
        }
    }

    @DrawableRes
    private fun getCheckedCakeColorIcon(color: CheckIconColorConstant): Int {
        return when (color) {
            CheckIconColorConstant.DISABLED -> {
                R.drawable.icon_disable_cake_checked
            }
            CheckIconColorConstant.STRAWBERRY -> {
                R.drawable.icon_strawberry_cake_checked
            }
            CheckIconColorConstant.ORANGE -> {
                R.drawable.icon_orange_cake_checked
            }
            CheckIconColorConstant.BLUE -> {
                R.drawable.icon_blue_cake_checked
            }
            CheckIconColorConstant.GREEN -> {
                R.drawable.icon_green_cake_checked
            }
            CheckIconColorConstant.BROWN -> {
                R.drawable.icon_brown_cake_checked
            }
        }
    }

    @DrawableRes
    private fun getUnCheckedCakeColorIcon(color: CheckIconColorConstant): Int {
        return when (color) {
            CheckIconColorConstant.DISABLED -> {
                R.drawable.icon_disable_cake_unchecked
            }
            CheckIconColorConstant.STRAWBERRY -> {
                R.drawable.icon_strawberry_cake_unchecked
            }
            CheckIconColorConstant.ORANGE -> {
                R.drawable.icon_orange_cake_unchecked
            }
            CheckIconColorConstant.BLUE -> {
                R.drawable.icon_blue_cake_unchecked
            }
            CheckIconColorConstant.GREEN -> {
                R.drawable.icon_green_cake_unchecked
            }
            CheckIconColorConstant.BROWN -> {
                R.drawable.icon_brown_cake_unchecked
            }
        }
    }

    class AddHabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.tv_habitName)
        private val first: ImageView = itemView.findViewById(R.id.iv_dayOfFirst)
        val second: ImageView = itemView.findViewById(R.id.iv_dayOfSecond)
        private val third: ImageView = itemView.findViewById(R.id.iv_dayOfThird)
        private val fourth: ImageView = itemView.findViewById(R.id.iv_dayOfFourth)
        private val fifth: ImageView = itemView.findViewById(R.id.iv_dayOfFifth)

        val iconArr = arrayOf(
            first, second, third, fourth, fifth
        )
    }

    class EditHabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitName: TextView = itemView.findViewById(R.id.tv_habitName)
        val deleteButton: ImageButton = itemView.findViewById(R.id.btn_removeHabit)
    }

    companion object {
        const val VIEW_TYPE_ADD_HABIT = 0
        const val VIEW_TYPE_HABIT = 1
        const val VIEW_TYPE_EDIT_HABIT = 2
    }

}