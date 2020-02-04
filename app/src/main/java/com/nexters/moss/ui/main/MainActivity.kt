package com.nexters.moss.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityMainBinding
import com.nexters.moss.ui.main.adapter.HabitItemTouchHelper
import com.nexters.moss.ui.main.adapter.HabitListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val vm: MainViewModel by viewModel()

    override fun getLayoutRes() = R.layout.activity_main
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupHabitRecyclerView()
    }

    private fun setupHabitRecyclerView() {
        val habitListAdapter = HabitListAdapter()
        val habitItemTouchCallback = HabitItemTouchHelper()
        val itemTouchHelper = ItemTouchHelper(habitItemTouchCallback)

        with(binding.rvHabitList) {
            adapter = habitListAdapter.apply {
                refreshItemList(ArrayList<String>().apply {
                    add("야식먹기")
                    add("늦잠자기")
                })
            }
            layoutManager = LinearLayoutManager(this@MainActivity)
            habitItemTouchCallback.attachItemTouchAdapter(habitListAdapter)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }
}
