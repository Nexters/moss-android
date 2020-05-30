package com.nexters.moss.ui.receive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.databinding.ActivityReceiveBinding
import com.nexters.moss.model.NewCakeModel
import com.nexters.moss.ui.dialog_report.ReceiveDialog
import com.nexters.moss.ui.diary.DiaryActivity
import com.nexters.moss.ui.send.SendActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class
ReceiveActivity : BaseActivity<ActivityReceiveBinding>() {
    override val vm: ReceiveViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_receive
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val newCake = intent.getParcelableExtra<NewCakeModel>(EXTRA_NEW_CAKE_MODEL)
        newCake?.let {
            vm.setNewCake(it)
        }
        observeViewModel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_SEND_CAKE -> {
                if (resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }

            REQUEST_CAKE_DIARY -> {
                if (resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }
        }
    }

    private fun observeViewModel() {
        with(vm) {
            report.observe(this@ReceiveActivity, Observer {
                if (it) {
                    ReceiveDialog().show(supportFragmentManager, "")
                }
            })

            exit.observe(this@ReceiveActivity, Observer {
                if (it) {
                    finish()
                }
            })

            diary.observe(this@ReceiveActivity, Observer {
                if (it) {
                    startActivityForResult(
                        Intent(applicationContext, DiaryActivity::class.java),
                        REQUEST_CAKE_DIARY
                    )
                }
            })

            send.observe(this@ReceiveActivity, Observer {
                if (it) {
                    startActivityForResult(
                        Intent(
                            applicationContext,
                            SendActivity::class.java
                        ).apply {
                            putExtra(SendActivity.COME_FROM, SendActivity.FROM_RECEIVE_CAKE)
                        }, REQUEST_SEND_CAKE
                    )
                }
            })

            backColor.observe(this@ReceiveActivity, Observer {
                window.statusBarColor = ContextCompat.getColor(this@ReceiveActivity, it)
            })

        }
    }


    companion object {
        const val REQUEST_SEND_CAKE = 1000
        const val REQUEST_CAKE_DIARY = 2000

        const val EXTRA_NEW_CAKE_MODEL = "extra_new_cake_model"
    }
}



