package com.nexters.moss.ui.dialog_first_gift.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.ui.dialog_first_gift.fragment.FirstGiftOneFragment
import com.nexters.moss.ui.dialog_first_gift.fragment.FirstGiftTwoFragment

class FirstGiftAdapter(fm: FragmentManager, private val cake: HabitListConstant) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FirstGiftOneFragment.newInstance(cake)
            else -> FirstGiftTwoFragment()
        }
    }

    override fun getCount() = 2
}