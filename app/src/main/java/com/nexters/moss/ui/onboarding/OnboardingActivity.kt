package com.nexters.moss.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.nexters.moss.R
import com.nexters.moss.ui.make_nickname.MakeNicknameActivity
import com.nexters.moss.ui.onboarding.adapter.OnboardingAdapter
import com.nexters.moss.utils.DLog
import com.nexters.moss.utils.KakaoLoginUtils
import com.nexters.moss.utils.KakaoSessionCallback
import kotlinx.android.synthetic.main.activity_onboarding.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class OnboardingActivity : AppCompatActivity() {
    lateinit var onboardingAdapter: OnboardingAdapter
    lateinit var first: ImageView
    lateinit var second: ImageView
    lateinit var third: ImageView
    lateinit var fourth: ImageView

    private val callback: KakaoSessionCallback by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        setupKakaoCallback()
        setupView()
        setViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()

        Session.getCurrentSession().removeCallback(callback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            GlobalScope.launch {
                if (callback.isOpening()) {
                    startNicknameActivityWithId(0)
                }
            }
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private suspend fun startNicknameActivityWithId(count: Int) {
        if (count == 5) {
            DLog.e("cannot get oAuth id")
        }
        DLog.d("repeat count is $count")

        val id = KakaoLoginUtils.getLoginId()

        if (id != KakaoLoginUtils.EMPTY) {
            startActivity(
                Intent(
                    applicationContext,
                    MakeNicknameActivity::class.java
                ).apply {
                    putExtra(MakeNicknameActivity.EXTRA_KAKAO_ID, id)
                })
            finish()
        } else {
            startNicknameActivityWithId(count + 1)
        }
    }

    private fun setupView() {
        first = firstIndicator
        second = secondIndicator
        third = thirdIndicator
        fourth = fourthIndicator
    }

    private fun setupKakaoCallback() {
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
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
                            changeResource(first, second, fourth)
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

    private fun changeResource(
        viewSelect: ImageView,
        viewDefault: ImageView,
        viewDefaultTwo: ImageView
    ) {
        viewSelect.setImageResource(R.drawable.icon_indicator_selected)
        viewDefault.setImageResource(R.drawable.icon_indicator_default)
        viewDefaultTwo.setImageResource(R.drawable.icon_indicator_default)
    }

}
