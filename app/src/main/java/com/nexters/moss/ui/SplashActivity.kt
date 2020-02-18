package com.nexters.moss.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.nexters.moss.R
import com.nexters.moss.ui.formation_habit.FormationHabitActivity
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.ui.make_nickname.MakeNicknameActivity
import com.nexters.moss.ui.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, OnboardingActivity::class.java))
            finish()
        },500)
    }
}