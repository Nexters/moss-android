package com.nexters.moss.ui.receive_dialog

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
}
