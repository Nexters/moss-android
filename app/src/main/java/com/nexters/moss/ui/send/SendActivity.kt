package com.nexters.moss.ui.send

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.R
import com.nexters.moss._base.BaseActivity
import com.nexters.moss.databinding.ActivitySendBinding
import com.nexters.moss.model.CakeModel
import com.nexters.moss.ui.main.MainActivity
import com.nexters.moss.ui.send.adapter.SendAdapter
import com.nexters.moss.ui.send.adapter.SendListDecoration
import kotlinx.android.synthetic.main.activity_send.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendActivity : BaseActivity<ActivitySendBinding>() {
    override val vm: SendViewModel by viewModel()
    override fun getLayoutRes() = R.layout.activity_send
    override fun setupBinding() {
        binding.vm = vm
    }

    private var cakeList = arrayListOf<CakeModel>(
        CakeModel("물마시기", R.drawable.send_select_watermelon),
        CakeModel("스트레칭", R.drawable.send_select_cheese),
        CakeModel("명상", R.drawable.send_select_cream),
        CakeModel("산책", R.drawable.send_select_green_tea),
        CakeModel("뉴스보기", R.drawable.send_select_coffee),
        CakeModel("아침먹기", R.drawable.send_select_apple),
        CakeModel("일기쓰기", R.drawable.send_select_chestnut),
        CakeModel("책읽기", R.drawable.send_select_almond)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.setCakeList(cakeList)

        setupCakeRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        with(vm) {

            vm.exit.observe(this@SendActivity, Observer {
                if (it) {
                    finish()
                }
            })

            main.observe(this@SendActivity, Observer {
                if (it) {
                    startActivity<MainActivity>()
                }
            })
        }
    }

    private fun setupCakeRecyclerView() {

        layout_send_cake_recycler.apply {
            adapter = SendAdapter()

            layoutManager = LinearLayoutManager(
                this@SendActivity, RecyclerView.HORIZONTAL, false
            )

            addItemDecoration(SendListDecoration())
        }
    }
}
