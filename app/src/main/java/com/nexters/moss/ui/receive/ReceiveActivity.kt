package com.nexters.moss.ui.receive

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityReceiveBinding
import com.nexters.moss.ui.diary.DiaryActivity
import com.nexters.moss.ui.send.SendActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveActivity : BaseActivity<ActivityReceiveBinding>() {
    override val vm: ReceiveViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_receive
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        with(vm) {

            report.observe(this@ReceiveActivity, Observer {

            })

            exit.observe(this@ReceiveActivity, Observer {
                if (it) {
                    finish()
                }
            })

            diary.observe(this@ReceiveActivity, Observer {
                if (it) {
                    startActivity<DiaryActivity>()
                }
            })

            send.observe(this@ReceiveActivity, Observer {
                if (it) {
                    startActivity<SendActivity>()
                }
            })
        }
    }

    private fun showToastReportCompleted() {

        Toast(applicationContext).apply {
            view = layoutInflater.inflate(R.layout.layout_toast_report, null)
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.TOP, Gravity.CENTER, Gravity.TOP)
            show()
        }
    }
}



