package com.nexters.moss.ui.dialog_logout

import android.content.res.Resources
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogLogoutBinding
import com.nexters.moss.databinding.DialogWithdrawBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutDialog : BaseDialog<DialogLogoutBinding>() {
    override val vm: LogoutViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_logout
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 220

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}