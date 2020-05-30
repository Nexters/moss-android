package com.nexters.moss.ui.dialog_withdraw

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.DialogWithdrawBinding
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WithdrawDialog : BaseDialog<DialogWithdrawBinding>() {
    override val vm: WithdrawViewModel by viewModel()

    private lateinit var sp: SharedPreferences

    override fun getLayoutRes() = R.layout.dialog_withdraw
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 220

    private var isWithdrawProcess = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnSubmit.setOnClickListener {
            if (isWithdrawProcess) {
                return@setOnClickListener
            }
            isWithdrawProcess = true

            withdraw()
        }
    }

    private fun observeViewModel() {
        with(vm) {
            isWithdrawFinish.observe(viewLifecycleOwner, Observer {
                if (it) {
                    sp.edit().run {
                        remove(SharedPreferenceConstant.ACCESS_TOKEN.getValue())
                        remove(SharedPreferenceConstant.HABIKERY_TOKEN.getValue())
                    }.apply()

                    dismiss()

                    with(activity as MainActivity) {
                        withdrawFinish()
                    }
                }
            })
        }
    }

    private fun withdraw() {
        activity?.let {
            sp = it.getUserSharedPreference()

            val habikeryToken = sp.getString(
                SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
                null
            ) ?: return

            vm.withdraw(habikeryToken)
        }
    }
}