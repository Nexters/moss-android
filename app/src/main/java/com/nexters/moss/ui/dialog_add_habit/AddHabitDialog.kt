package com.nexters.moss.ui.dialog_add_habit

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogAddHabitBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddHabitDialog : BaseDialog<DialogAddHabitBinding>() {
    override val vm: AddHabitViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_add_habit
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