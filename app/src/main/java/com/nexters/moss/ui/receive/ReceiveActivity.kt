package com.nexters.moss.ui.receive

import android.app.AlertDialog
import android.os.Bundle
import android.view.ContextThemeWrapper
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityReceiveBinding
import kotlinx.android.synthetic.main.activity_receive.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveActivity : BaseActivity<ActivityReceiveBinding>() {
    override val vm: ReceiveViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_receive
    override fun setupBinding() {
        binding.vm = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn_receive_report.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        AlertDialog.Builder(
            ContextThemeWrapper(
                this@ReceiveActivity,
                R.style.Theme_AppCompat_Light_Dialog
            )
        ).apply {
            setView(layoutInflater.inflate(R.layout.dialog_report, null))

            setPositiveButton("확인") { _, _ ->
                toast("alert ok")
            }

            setNegativeButton("취소") { _, _ ->
                toast("alert cancel")
            }

            show()
        }

    }

}

