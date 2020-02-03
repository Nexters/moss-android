package com.nexters.moss.ui.diary

import android.os.Bundle
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityDiaryBinding
import com.nexters.moss.ui.diary.adapter.DiaryViewPagerAdapter
import kotlinx.android.synthetic.main.activity_diary.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryActivity : BaseActivity<ActivityDiaryBinding>() {
    override val vm: DiaryViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_diary
    override fun setupBinding() {
        binding.vm = this
    }

    lateinit var diaryAdapter: DiaryViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setViewPager()
    }

    private fun setViewPager(){
        diaryAdapter = DiaryViewPagerAdapter(supportFragmentManager)

        layout_diary_view_pager.adapter = diaryAdapter

        with(layout_diary_tab_layout) {
            setupWithViewPager(layout_diary_view_pager)
            clearOnTabSelectedListeners()
        }
    }
}