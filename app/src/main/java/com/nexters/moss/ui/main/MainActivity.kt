package com.nexters.moss.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.ActivityMainBinding
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.extension.showHabikeryToast
import com.nexters.moss.ui.dialog_first_gift.FirstGiftDialog
import com.nexters.moss.ui.dialog_logout.LogoutDialog
import com.nexters.moss.ui.dialog_remove_habit.RemoveHabitDialog
import com.nexters.moss.ui.dialog_withdraw.WithdrawDialog
import com.nexters.moss.ui.diary.DiaryActivity
import com.nexters.moss.ui.main.adapter.HabitItemTouchHelper
import com.nexters.moss.ui.main.adapter.HabitListAdapter
import com.nexters.moss.ui.onboarding.OnboardingActivity
import com.nexters.moss.ui.receive.ReceiveActivity
import com.nexters.moss.ui.send.SendActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val vm: MainViewModel by viewModel()

    private val habitListAdapter = HabitListAdapter()

    private var lastBackPressedTime = -1L
    private var finishToast: Toast? = null

    private val sp by lazy {
        getUserSharedPreference()
    }

    private val habikeryToken by lazy {
        sp.getString(
            SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
            null
        )
    }

    override fun getLayoutRes() = R.layout.activity_main
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        binding.tvNickname.setOnClickListener {
//            startActivity<ReceiveActivity>()
//        }
        setupNickname()
        setupHabitRecyclerView()
        setupDrawerLayout()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        setupHabitList()
        vm.refreshDate()
    }

    override fun onBackPressed() {
        if (vm.isOpenDrawer.value!!) {
            vm.setDrawerState(false)
        } else {
            if (vm.isEditMode.value!!) {
                vm.setEditMode(false)
                return
            }

            if (System.currentTimeMillis() > lastBackPressedTime + 2000) {
                lastBackPressedTime = System.currentTimeMillis()
                finishToast = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누를시 종료됩니다.", Toast.LENGTH_SHORT)
                finishToast?.show()
            } else {
                finishToast?.cancel()
                super.onBackPressed()
            }
        }
    }

    private fun observeViewModel() {
        with(vm) {
            isWithDraw.observe(this@MainActivity, Observer {
                if (it) {
                    WithdrawDialog().show(supportFragmentManager, "")
                }
            })

            isLogout.observe(this@MainActivity, Observer {
                if (it) {
                    LogoutDialog().show(supportFragmentManager, "")
                }
            })
            isEditMode.observe(this@MainActivity, Observer {
                habitListAdapter.setEditMode(it)
            })
            intentDiary.observe(this@MainActivity, Observer {
                if (it) {
                    startActivity<DiaryActivity>()
                }
            })
            intentSend.observe(this@MainActivity, Observer {
                if (it) {
                    startActivity(Intent(applicationContext, SendActivity::class.java).apply {
                        putExtra(SendActivity.COME_FROM, SendActivity.FROM_MAIN_SEND_CAKE)
                    })
                }
            })
            receivedCake.observe(this@MainActivity, Observer {
                startActivity(Intent(applicationContext, ReceiveActivity::class.java).apply {
                    putExtra(ReceiveActivity.EXTRA_NEW_CAKE_MODEL, it)
                })
            })
            firstCheckEvent.observe(this@MainActivity, Observer {
                val habit = HabitListConstant.values()[it - 1]
                FirstGiftDialog.newInstance(habit).show(supportFragmentManager, "")
            })
        }

        // 임시 첫 선물 팝업 띄우기 기능
        binding.txtPush.setOnClickListener {

        }
    }

    private fun setupHabitRecyclerView() {
        val habitItemTouchCallback = HabitItemTouchHelper()
        val itemTouchHelper = ItemTouchHelper(habitItemTouchCallback)

        with(binding.rvHabitList) {
            adapter = habitListAdapter

            habitListAdapter.setOnDeleteButtonClickListener { habitId, habitName ->
                RemoveHabitDialog().apply {
                    val bundle = Bundle()
                    bundle.putInt(RemoveHabitDialog.ARGUMENT_CATEGORY_ID, habitId)
                    arguments = bundle
                    setOnDissmissListener {
                        showHabikeryToast("$habitName 습관이 삭제되었습니다.")
                        setupHabitList()
                    }
                }.run {
                    show(supportFragmentManager, "")
                }
            }

            habitListAdapter.setOnCheckButtonClickListener {
                vm.doneHabit(habikeryToken ?: return@setOnCheckButtonClickListener, it)
            }

            habitListAdapter.setOnItemOrderChangeListener { habitId, to ->
                vm.changeOrderHabit(
                    habikeryToken ?: return@setOnItemOrderChangeListener,
                    habitId,
                    to
                )
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

    private fun setupHabitList() {
        vm.refreshItemList(habikeryToken ?: return)
    }

    private fun setupNickname() {
        vm.setNickname(habikeryToken ?: return)
    }

    fun withdrawFinish() {
        startActivity<OnboardingActivity>()
        finish()
    }
}
