package com.nexters.moss.ui.dialog_first_gift

import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.databinding.DialogFirstGiftBinding
import com.nexters.moss.ui.dialog_first_gift.adapter.FirstGiftAdapter
import kotlinx.android.synthetic.main.dialog_first_gift.view.*
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
        val cake = arguments?.getParcelable(KIND_OF_CAKE) ?: HabitListConstant.WALK

        with(binding.vpFirstGift) {
            adapter = FirstGiftAdapter(childFragmentManager, cake)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            binding.ivFirstIndicator.setImageResource(R.drawable.icon_indicator_selected)
                            binding.ivSecondIndicator.setImageResource(R.drawable.icon_indicator_default)
                        }

                        1 -> {
                            binding.ivFirstIndicator.setImageResource(R.drawable.icon_indicator_default)
                            binding.ivSecondIndicator.setImageResource(R.drawable.icon_indicator_selected)
                        }
                    }
                }
            })
        }
    }

    companion object {
        private const val KIND_OF_CAKE = "kind_of_cake"

        @JvmStatic
        fun newInstance(cake: HabitListConstant): FirstGiftDialog {
            val args = Bundle()
            args.putParcelable(KIND_OF_CAKE, cake)

            return FirstGiftDialog().apply {
                arguments = args
            }
        }
    }
}