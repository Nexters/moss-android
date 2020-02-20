package com.nexters.moss.ui.dialog_remove_habit

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.DialogRemoveHabitBinding
import com.nexters.moss.extension.getUserSharedPreference
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoveHabitDialog : BaseDialog<DialogRemoveHabitBinding>() {
    override val vm: RemoveHabitViewModel by viewModel()

    private var onDissmissListener: OnDissmissListener? = null

    override fun getLayoutRes() = R.layout.dialog_remove_habit
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 220

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnSubmit.setOnClickListener {
            deleteHabit()
        }
    }

    private fun observeViewModel() {
        with(vm) {
            isFinishDelete.observe(viewLifecycleOwner, Observer {
                if (it) {
                    dismiss()
                    onDissmissListener?.onDismiss()
                }
            })
        }
    }

    private fun deleteHabit() {
        activity?.let {
            val sp = it.getUserSharedPreference()

            val habikeryToken = sp.getString(
                SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
                null
            ) ?: return

            val categoryId = arguments?.getInt(ARGUMENT_CATEGORY_ID) ?: return

            vm.deleteHabit(habikeryToken, categoryId)
        }
    }

    fun setOnDissmissListener(listener: () -> Unit) {
        onDissmissListener = object : OnDissmissListener {
            override fun onDismiss() {
                listener()
            }
        }
    }

    interface OnDissmissListener {
        fun onDismiss()
    }

    companion object {
        const val ARGUMENT_CATEGORY_ID = "argument_category_id"
    }
}