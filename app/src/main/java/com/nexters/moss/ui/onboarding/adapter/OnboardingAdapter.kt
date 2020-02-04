package com.nexters.moss.ui.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nexters.moss.ui.onboarding.OnboardingFourFragment
import com.nexters.moss.ui.onboarding.OnboardingOneFragment
import com.nexters.moss.ui.onboarding.OnboardingThreeFragment
import com.nexters.moss.ui.onboarding.OnboardingTwoFragment

class OnboardingAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> {
                OnboardingOneFragment()
            }
            1-> {
                OnboardingTwoFragment()
            }
            2-> {
                OnboardingThreeFragment()
            }
            else -> {
                OnboardingFourFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }
}