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
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.ui.main.MainActivity
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
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun startNicknameActivityWithId() {
        GlobalScope.launch {
            val id = KakaoLoginUtils.getAccessToken()
            val sp = getUserSharedPreference()
            val isLogout = sp.getBoolean(SharedPreferenceConstant.IS_LOGOUT.getValue(), false)

            if (isLogout) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                sp.edit().remove(SharedPreferenceConstant.IS_LOGOUT.getValue()).apply()
            } else {
                startActivity(
                    Intent(
                        applicationContext,
                        MakeNicknameActivity::class.java
                    ).apply {
                        putExtra(MakeNicknameActivity.EXTRA_KAKAO_ID, id)
                    }
                )
            }

            finish()
        }
    }

    private fun setupView() {
        first = firstIndicator
        second = secondIndicator
        third = thirdIndicator
        fourth = fourthIndicator
    }

    private fun setupKakaoCallback() {
        callback.setOnSessionOpenedCallback {
            startNicknameActivityWithId()
        }
        Session.getCurrentSession().addCallback(callback)
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
