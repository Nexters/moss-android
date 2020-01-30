package com.nexters.moss.ui.send

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivitySendBinding
import com.nexters.moss.model.CakeModel
import com.nexters.moss.ui.send.adapter.SendAdapter
import kotlinx.android.synthetic.main.activity_send.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendActivity : BaseActivity<ActivitySendBinding>() {
    override val vm: SendViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_send
    override fun setupBinding() {
        binding.vm = vm
    }

    private var cakeList = arrayListOf<CakeModel>(
        CakeModel("하나"),
        CakeModel("둘"),
        CakeModel("셋"),
        CakeModel("넷"),
        CakeModel("다섯"),
        CakeModel("여섯"),
        CakeModel("일곱")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupCakeRecyclerView()

        vm.setCakeList(cakeList)
    }

    private fun setupCakeRecyclerView() {

        with(layout_send_cake_recycler) {
            // adapter 연결하기
            adapter = SendAdapter()

            // layoutManager 연결하기
            layoutManager = LinearLayoutManager(
                this@SendActivity, RecyclerView.HORIZONTAL, false
            )

            // 리사이클러뷰 사이즈 고정
            setHasFixedSize(true)
        }
    }
}
