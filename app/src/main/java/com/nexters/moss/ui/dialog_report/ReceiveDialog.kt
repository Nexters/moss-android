package com.nexters.moss.ui.dialog_report

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogReportBinding
import com.nexters.moss.extension.showHabikeryToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveDialog : BaseDialog<DialogReportBinding>() {
    override val vm: ReceiveDialogViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_report

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
                    activity?.showHabikeryToast("신고가 접수되었습니다.")
                    dismiss()
                }
            })

            cancel.observe(viewLifecycleOwner, Observer {
                if (it) {
                    dismiss()
                }
            })

            report.observe(viewLifecycleOwner, Observer {
                if (it != 0) {
                    itemSelected()
                }
            })
        }
    }
}
