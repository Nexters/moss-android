package com.nexters.moss.ui.main

import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivityMainBinding
import com.nexters.moss.databinding.ActivitySendBinding
import com.nexters.moss.ui.send.CakeModel
import com.nexters.moss.ui.send.SendAdapter
import com.nexters.moss.ui.send.SendViewModel
import kotlinx.android.synthetic.main.activity_send.*

class SendActivity : BaseActivity<ActivitySendBinding, SendViewModel>(SendViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_send
    override fun setupBinding() {
        binding.vm = viewModel
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

        // adapter 연결하기
        layout_send_cake_recycler.adapter = SendAdapter(this, cakeList)

        // layoutManager 연결하기
        layout_send_cake_recycler.layoutManager = LinearLayoutManager(this,
            RecyclerView.HORIZONTAL,false)

        // 리사이클러뷰 사이즈 고정
        layout_send_cake_recycler.setHasFixedSize(true)


    }
}
