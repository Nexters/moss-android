package com.nexters.moss.ui.formation_habit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityFormationHabitBinding
import com.nexters.moss.ui.dialog_add_habit.AddHabitDialog
import com.nexters.moss.ui.formation_habit.adapter.FormationHabitListAdapter
import com.nexters.moss.ui.formation_habit.adapter.FormationHabitListDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormationHabitActivity : BaseActivity<ActivityFormationHabitBinding>() {
    override val vm: FormationHabitViewModel by viewModel()

    override fun getLayoutRes() = R.layout.activity_formation_habit

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupFormationHabitRecyclerView()
        observeViewModel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    private fun observeViewModel() {
        with(vm) {
            isClose.observe(this@FormationHabitActivity, Observer {
                finish()
            })
            isMakeHabit.observe(this@FormationHabitActivity, Observer {
                AddHabitDialog().show(supportFragmentManager, "")
            })
            existToastValue.observe(this@FormationHabitActivity, Observer {
                toast(it)
            })
        }
    }

    private fun setupFormationHabitRecyclerView() {
        with(binding.rvFormationHabitList) {
            adapter = FormationHabitListAdapter().apply {
                refreshItemList(vm.formationHabitList)
                setOnItemClickListener {
                    vm.selectItem(it)
                }
            }
            layoutManager = GridLayoutManager(this@FormationHabitActivity, 2)
            addItemDecoration(FormationHabitListDecoration())
        }
    }
}
