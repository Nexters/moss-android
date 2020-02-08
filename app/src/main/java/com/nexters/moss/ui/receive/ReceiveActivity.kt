package com.nexters.moss.ui.receive

import android.app.AlertDialog
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityReceiveBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveActivity : BaseActivity<ActivityReceiveBinding>() {
    override val vm: ReceiveViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_receive
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.report.observe(this, Observer {
            if (vm.report.value!!) {
                showDialog()
            }
        })

    }

    private fun showDialog() {

        val dialogView = layoutInflater.inflate(R.layout.layout_dialog_report, null)

        dialogView.findViewById<RadioGroup>(R.id.radio_group_report)
            .apply {
                setOnCheckedChangeListener { _, id ->
                    when (id) {
                        R.id.btn_report_offensive -> toast("1")
                        R.id.btn_report_inappropriate -> toast("2")
                        R.id.btn_report_spam -> toast("3")
                        R.id.btn_report_other -> toast("4")
                    }
                }
            }

        AlertDialog.Builder(
            ContextThemeWrapper(
                this@ReceiveActivity,
                R.style.Theme_AppCompat_Light_Dialog
            )
        ).apply {

            setTitle("신고사유")

            setView(dialogView)

            setPositiveButton("확인") { _, _ ->
                vm.setBtnReportStatus(false)
                showToastReportCompleted()
            }

            setNegativeButton("취소") { _, _ ->
                vm.setBtnReportStatus(false)
            }

            show()
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



