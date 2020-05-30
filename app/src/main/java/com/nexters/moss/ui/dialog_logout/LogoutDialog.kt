package com.nexters.moss.ui.dialog_logout

import android.content.res.Resources
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.DialogLogoutBinding
import com.nexters.moss.databinding.DialogWithdrawBinding
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutDialog : BaseDialog<DialogLogoutBinding>() {
    override val vm: LogoutViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_logout
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 220

    private var isLogoutProcess = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnSubmit.setOnClickListener {
            if (isLogoutProcess) {
                return@setOnClickListener
            }
            isLogoutProcess = true

            vm.logout()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        with(vm) {
            loginSuccessEvent.observe(this@LogoutDialog, Observer {
                dismiss()

                with(activity as MainActivity) {
                    withdrawFinish()
                    val sp = getUserSharedPreference()
                    sp.edit().run {
                        putBoolean(SharedPreferenceConstant.IS_LOGOUT.getValue(), true)
                    }.apply()
                }
            })
        }
    }
}