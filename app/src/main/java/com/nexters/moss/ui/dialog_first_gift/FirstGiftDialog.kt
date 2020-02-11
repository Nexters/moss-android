package com.nexters.moss.ui.dialog_first_gift

import android.os.Bundle
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogFirstGiftBinding
import com.nexters.moss.ui.dialog_first_gift.adapter.FirstGiftAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstGiftDialog : BaseDialog<DialogFirstGiftBinding>() {
    override val vm: FirstGiftViewModel by viewModel()

    override fun getLayoutRes() = R.layout.dialog_first_gift

    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 340
    override fun getDialogHeight() = 400


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        val vp = binding.vpFirstGift
        val adapter = FirstGiftAdapter(childFragmentManager)
        vp.adapter = adapter

        with(binding.indicatorFirstGift) {
            setupWithViewPager(vp)
            clearOnTabSelectedListeners()
        }
    }
}