package com.nexters.moss.ui.dialog_receive_cake

import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogReceiveCakeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveCakeDialog : BaseDialog<DialogReceiveCakeBinding>() {
    override val vm: ReceiveCakeViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_receive_cake
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 220

}