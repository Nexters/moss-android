package com.nexters.moss.ui.dialog_remove_habit

import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogRemoveHabitBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoveHabitDialog : BaseDialog<DialogRemoveHabitBinding>() {
    override val vm: RemoveHabitViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_remove_habit
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 220
}