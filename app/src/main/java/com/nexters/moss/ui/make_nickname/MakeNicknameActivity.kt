package com.nexters.moss.ui.make_nickname

import android.os.Bundle
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityMakeNicknameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MakeNicknameActivity : BaseActivity<ActivityMakeNicknameBinding>() {
    override val vm: MakeNicknameViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_make_nickname
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
