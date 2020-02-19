package com.nexters.moss.ui.dialog_report

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogReportBinding
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
                    dismiss()
                    showToastReportCompleted()

//                    toast(" "+vm.report.value)
//
//                    if(vm.report.value == 3){
//                        if(vm.report.value == null)
//                            toast("기타")
//                        else
//                            toast(" "+vm.reason.value)
//                    }
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

    private fun showToastReportCompleted() {

        val toastTopValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            21f,
            view!!.resources.displayMetrics
        ).toInt()


        Toast(context).apply {
            view = layoutInflater.inflate(R.layout.layout_toast_report, null)
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, toastTopValue)
            show()
        }
    }
}
