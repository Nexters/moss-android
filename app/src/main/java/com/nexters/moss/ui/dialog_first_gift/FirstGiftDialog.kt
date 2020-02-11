package com.nexters.moss.ui.dialog_first_gift

import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogFirstGiftBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstGiftDialog : BaseDialog<DialogFirstGiftBinding>() {
    override val vm: FirstGiftViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_first_gift

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 280
    override fun getDialogHeight() = 360

}