package com.nexters.moss.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nexters.moss.R
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.ui.formation_habit.FormationHabitActivity
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.ui.onboarding.OnboardingActivity
import com.nexters.moss.utils.DLog
import com.nexters.moss.utils.KakaoLoginUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val userRepo: UserRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            delay(1000)

            startActivity(Intent(applicationContext, OnboardingActivity::class.java))
            finish()
        }
    }

    private suspend fun isExistId(): Boolean {
        val sp = getUserSharedPreference()

        val accessToken = sp.getString(
            SharedPreferenceConstant.ACCESS_TOKEN.getValue(),
            null
        ) ?: SharedPreferenceConstant.UNKNOWN.getValue()

        val isExist = accessToken != SharedPreferenceConstant.UNKNOWN.getValue()

        if (isExist) {
            val token = userRepo.login(accessToken)
            DLog.d("token = ${token.habikeryToken ?: ""}")
            sp.edit().putString(
                SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
                token.habikeryToken
            ).apply()
        }

        return isExist
    }
}