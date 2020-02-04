package com.nexters.moss.ui.formation_habit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityFormationHabitBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormationHabitActivity : BaseActivity<ActivityFormationHabitBinding>() {
    override val vm: FormationHabitViewModel by viewModel()

    override fun getLayoutRes() = R.layout.activity_formation_habit

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
