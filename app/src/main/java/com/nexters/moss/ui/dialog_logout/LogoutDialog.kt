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

    override fun getDialogWidth() = (280 * resources.displayMetrics.density + 0.5f).toInt()
    override fun getDialogHeight() = (200 * resources.displayMetrics.density + 0.5f).toInt()
}