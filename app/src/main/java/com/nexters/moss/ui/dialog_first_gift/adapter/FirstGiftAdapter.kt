package com.nexters.moss.ui.dialog_first_gift.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nexters.moss.ui.dialog_first_gift.FirstGiftOneFragment
import com.nexters.moss.ui.dialog_first_gift.FirstGiftTwoFragment

class FirstGiftAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FirstGiftOneFragment()
            else -> FirstGiftTwoFragment()
        }
    }

    override fun getCount() = 2

}