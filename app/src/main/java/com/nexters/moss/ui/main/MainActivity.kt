package com.nexters.moss.ui.main

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityMainBinding
import com.nexters.moss.ui.dialog_withdraw.WithdrawDialog
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
        setupDrawerLayout()
        observeViewModel()
    }

    override fun onBackPressed() {
        if (vm.isOpenDrawer.value!!) {
            vm.setDrawerState(false)
        } else {
            super.onBackPressed()
        }
    }

    private fun observeViewModel() {
        with(vm) {
            isWithDraw.observe(this@MainActivity, Observer {
                if (it) {
                    WithdrawDialog().show(supportFragmentManager, "")
                }
            })
        }
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

    private fun setupDrawerLayout() {
        with(binding.dlSettingDrawer) {
            addDrawerListener(object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) = Unit
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) = Unit
                override fun onDrawerClosed(drawerView: View) {
                    vm.setDrawerState(false)
                }
                override fun onDrawerOpened(drawerView: View) {
                    vm.setDrawerState(true)
                }
            })

            setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }
}
