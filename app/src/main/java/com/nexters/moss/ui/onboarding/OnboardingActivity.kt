package com.nexters.moss.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.nexters.moss.R
import com.nexters.moss.ui.make_nickname.MakeNicknameActivity
import com.nexters.moss.ui.onboarding.adapter.OnboardingAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingActivity : AppCompatActivity() {

    lateinit var onboardingAdapter: OnboardingAdapter
    lateinit var first: ImageView
    lateinit var second: ImageView
    lateinit var third: ImageView
    lateinit var fourth: ImageView

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

        first = firstIndicator
        second = secondIndicator
        third = thirdIndicator
        fourth = fourthIndicator
    }

    private fun setViewPager() {

        onboardingAdapter = OnboardingAdapter(supportFragmentManager)

        layout_onboarding_view_pager.apply {
            adapter = onboardingAdapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            changeResource(first,second,fourth)
                        }
                        1 -> {
                            changeResource(second, first, third)

                        }
                        2 -> {
                            changeResource(third, second, fourth)
                        }
                        3 -> {
                            changeResource(fourth, third, first)
                        }
                    }
                }
            })

        }


    }

    private fun changeResource(viewSelect : ImageView, viewDefault : ImageView, viewDefaultTwo : ImageView ){
        viewSelect.setImageResource(R.drawable.icon_indicator_selected)
        viewDefault.setImageResource(R.drawable.icon_indicator_default)
        viewDefaultTwo.setImageResource(R.drawable.icon_indicator_default)
    }

}
