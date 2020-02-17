package com.nexters.moss.ui.dialog_first_gift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nexters.moss.R
import com.nexters.moss.constant.HabitListConstant
import kotlinx.android.synthetic.main.fragment_first_gift_one.view.*

class FirstGiftOneFragment : Fragment() {
    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        v = inflater.inflate(R.layout.fragment_first_gift_one, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getParcelable<HabitListConstant>(KIND_OF_CAKE)?.let {

            val color = it.getSecondaryColor()
            val cakeImage = it.getBgImage()
            val cakeName = it.getCakeName()

            v.ll_cakeWrapper.setBackgroundResource(color)
            v.iv_cake.setImageResource(cakeImage)
            v.tv_cakeName.text = cakeName
        }
    }

    companion object {
        private const val KIND_OF_CAKE = "cake_kind"

        @JvmStatic
        fun newInstance(cake: HabitListConstant): FirstGiftOneFragment {
            val args = Bundle()
            args.putParcelable(KIND_OF_CAKE, cake)
            return FirstGiftOneFragment().apply {
                arguments = args
            }
        }
    }
}