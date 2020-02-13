package com.nexters.moss.ui.receive_dialog

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogReceiveBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveDialog : BaseDialog<DialogReceiveBinding>() {
    override val vm: ReceiveDialogViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_receive

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 350
    override fun getDialogHeight() = 400

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel()

    }

    private fun observeViewModel() {
        with(vm) {
            submit.observe(viewLifecycleOwner, Observer {
                if (it) {
                    dismiss()
                    showToastReportCompleted()
                }
            })

            cancel.observe(viewLifecycleOwner, Observer {
                if (it) {
                    dismiss()
                }
            })
        }
    }

    private fun showToastReportCompleted() {

        Toast(context).apply {
            view = layoutInflater.inflate(R.layout.layout_toast_report, null)
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.TOP, Gravity.CENTER, Gravity.TOP)
            show()
        }
    }
}
