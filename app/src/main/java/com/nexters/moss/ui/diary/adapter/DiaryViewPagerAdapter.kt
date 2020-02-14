package com.nexters.moss.ui.diary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nexters.moss.ui.diary.DiaryPieceFragment
import com.nexters.moss.ui.diary.DiaryWholeFragment

class DiaryViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                DiaryPieceFragment()
            }
            else ->{
                DiaryWholeFragment()
            }
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"   조각케익   "
            else->"   홀케익   "
        }
    }
}