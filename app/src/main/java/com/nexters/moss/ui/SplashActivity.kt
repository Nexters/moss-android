package com.nexters.moss.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.Session
import com.nexters.moss.R
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.ui.make_nickname.MakeNicknameActivity
import com.nexters.moss.ui.onboarding.OnboardingActivity
import com.nexters.moss.utils.DLog
import com.nexters.moss.utils.KakaoLoginUtils
import com.nexters.moss.utils.KakaoSessionCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private var autoLogin = false

    private val callback: KakaoSessionCallback by inject()
    private val userRepo: UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            delay(1000)
            setupKakaoCallback()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Session.getCurrentSession().removeCallback(callback)
    }

    private fun setupKakaoCallback() {
        callback.setOnSessionOpenedCallback {
            startNicknameActivityWithId()
        }
        Session.getCurrentSession().addCallback(callback)
        autoLogin = Session.getCurrentSession().checkAndImplicitOpen()
        DLog.d("is Can Open: $autoLogin")

        if (autoLogin.not()) {
            startActivity(Intent(applicationContext, OnboardingActivity::class.java))
            finish()
        }
    }

    private fun startNicknameActivityWithId() {
        GlobalScope.launch {
            val id = KakaoLoginUtils.getAccessToken()
            if (autoLogin) {
                DLog.d("auto!")
                saveHabikeryTokenInSharedPreference()
                startActivity(Intent(applicationContext, MainActivity::class.java))
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

    private suspend fun saveHabikeryTokenInSharedPreference() {
        val sp = getUserSharedPreference()

        val accessToken = Session.getCurrentSession().tokenInfo.accessToken


        val token = userRepo.login(accessToken)

        sp.edit().run {
            putString(
                SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
                token.habikeryToken
            )
        }.apply()
    }
}