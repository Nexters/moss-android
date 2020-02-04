package com.nexters.moss.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.nexters.moss.ui.formation_habit.FormationHabitActivity
import com.nexters.moss.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(applicationContext, FormationHabitActivity::class.java))
        },500)
    }
}