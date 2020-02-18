package com.nexters.moss.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.nexters.moss.R
import com.nexters.moss.ui.make_nickname.MakeNicknameActivity
import com.nexters.moss.ui.onboarding.adapter.OnboardingAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingActivity : AppCompatActivity(){

    lateinit var onboardingAdapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupView()
        setViewPager()
    }

    private fun setupView() {
        btn_startByKakao.setOnClickListener {
            startActivity(Intent(applicationContext, MakeNicknameActivity::class.java))
            finish()
        }
    }

    private fun setViewPager() {

        onboardingAdapter = OnboardingAdapter(supportFragmentManager)

        layout_onboarding_view_pager.adapter = onboardingAdapter

        with(indicator_onboarding) {
            setupWithViewPager(layout_onboarding_view_pager)
            clearOnTabSelectedListeners()
            tabSelectedIndicator
        }
    }
}