package com.nexters.moss.ui.dialog_add_habit

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogAddHabitBinding
import com.nexters.moss.ui.formation_habit.FormationHabitActivity
import com.nexters.moss.ui.send.SendActivity
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddHabitDialog : BaseDialog<DialogAddHabitBinding>() {
    override val vm: AddHabitViewModel by viewModel()

    private var isStartActivity = false

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

        binding.btnSubmit.setOnClickListener {
            activity?.let {
                if (it is FormationHabitActivity) {
                    startActivity(Intent(it, SendActivity::class.java).apply {
                        putExtra(SendActivity.COME_FROM, SendActivity.FROM_ADD_HABIT)
                        putExtra(SendActivity.CREATE_CATEGORY_ID, it.vm.getSelectedItem())
                    })
                }
            }
        }
    }

    override fun onPause() {
        dialog?.dismiss()
        super.onPause()
    }
}