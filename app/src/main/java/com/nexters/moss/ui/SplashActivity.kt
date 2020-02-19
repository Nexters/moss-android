package com.nexters.moss.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nexters.moss.R
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.ui.onboarding.OnboardingActivity
import com.nexters.moss.utils.DLog
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
            val isExist = isExistId()

            val clazz: Class<out AppCompatActivity> = if (isExist) {
                MainActivity::class.java
            } else {
                OnboardingActivity::class.java
            }
            delay(500)

            startActivity(Intent(applicationContext, clazz))
            finish()
        }
    }

    private suspend fun isExistId(): Boolean {
        val sp = getSharedPreferences(
            SharedPreferenceConstant.USER_PREFERENCE_NAME.getValue(),
            Context.MODE_PRIVATE
        )

        val accessToken = sp.getString(
            SharedPreferenceConstant.ACCESS_TOKEN.getValue(),
            SharedPreferenceConstant.UNKNOWN.getValue()
        ) ?: ""

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