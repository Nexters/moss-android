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
import com.nexters.moss.model.HabitModel
import com.nexters.moss.ui.formation_habit.FormationHabitActivity
import com.nexters.moss.utils.DLog
import java.util.*
import kotlin.collections.ArrayList

class HabitListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HabitItemTouchHelper.OnItemTouchListener {

    private var itemList = ArrayList<HabitModel>()
    private lateinit var context: Context
    private var isEditMode = false

    private var onDeleteButtonClickListener: OnDeleteButtonClickListener? = null
    private var onCheckButtonClickListener: OnCheckButtonClickListener? = null
    private var onItemOrderChangeListener: OnItemOrderChangeListener? = null

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
                holder.habitName.text = translateItem(item.name)
                val color =
                    context.resources.getColor(HabitListConstant.getPersonalColor(translateItem(item.name)), null)
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
                    if (h.getHabitName() == translateItem(item.name)) {
                        habit = h
                    }
                }
                var isToday: Boolean
                for (i in holder.iconArr.indices) {
                    stateList[i] = when(item.records[i].habitStatus.toLowerCase()) {
                        "done" -> CheckIconConstant.CHECKED
                        "not_done" -> CheckIconConstant.UNCHECKED
                        "cake_done" -> CheckIconConstant.CHECKED_CAKE
                        "cake_not_done" -> CheckIconConstant.UNCHECKED_CAKE
                        else -> CheckIconConstant.UNCHECKED
                    }
                    isToday = false
                    if (i == 1)
                        isToday = true
                    holder.iconArr[i].setImageResource(getCheckIcon(stateList[i], habit, isToday))
                }

                holder.second.setOnClickListener {
                    if (!(stateList[1] == CheckIconConstant.CHECKED ||
                        stateList[1] == CheckIconConstant.CHECKED_CAKE)) {

                        onCheckButtonClickListener?.onCheckButtonClick(item.habitId)
                    }
                }

            }
            is EditHabitViewHolder -> {
                val item = itemList[position]
                holder.habitName.text = translateItem(item.name)
                val color =
                    context.resources.getColor(HabitListConstant.getPersonalColor(translateItem(item.name)), null)
                holder.habitName.setTextColor(color)

                holder.deleteButton.setOnClickListener {
                    onDeleteButtonClickListener?.onDeleteButtonClick(item.habitId, translateItem(item.name))
                }
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
            onItemOrderChangeListener?.onItemOrderChange(itemList[toPosition].habitId, toPosition)
            DLog.d("hehlehlelhlehlelhlel")
        }
    }

    fun refreshItemList(list: ArrayList<HabitModel>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun setEditMode(enabled: Boolean) {
        isEditMode = enabled
        notifyDataSetChanged()
    }

    fun getEditMode() = isEditMode

    private fun translateItem(item: String): String {
        return when (item.toLowerCase()) {
            "water" -> "물마시기"
            "stretching" -> "스트레칭"
            "meditation" -> "명상"
            "walk" -> "산책"
            "news" -> "뉴스보기"
            "breakfast" -> "아침식사"
            "diary" -> "일기쓰기"
            "reading" -> "책읽기"
            else -> "딴짓하기"
        }

    }

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

    fun setOnDeleteButtonClickListener(listener: (categoryId: Int, habitName: String) -> Unit) {
        onDeleteButtonClickListener = object : OnDeleteButtonClickListener {
            override fun onDeleteButtonClick(habitId: Int, habitName: String) {
                listener(habitId, habitName)
            }
        }
    }

    fun setOnCheckButtonClickListener(listener: (habitId: Int) -> Unit) {
        onCheckButtonClickListener = object : OnCheckButtonClickListener {
            override fun onCheckButtonClick(habitId: Int) {
                listener(habitId)
            }
        }
    }

    fun setOnItemOrderChangeListener(listener: (habitId: Int, to: Int) -> Unit) {
        onItemOrderChangeListener = object : OnItemOrderChangeListener {
            override fun onItemOrderChange(habitId: Int, to: Int) {
                listener(habitId, to)
            }
        }
    }

    interface OnItemOrderChangeListener {
        fun onItemOrderChange(habitId: Int, to: Int)
    }

    interface OnDeleteButtonClickListener {
        fun onDeleteButtonClick(habitId: Int, habitName: String)
    }

    interface OnCheckButtonClickListener {
        fun onCheckButtonClick(habitId: Int)
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