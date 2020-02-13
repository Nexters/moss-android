package com.nexters.moss.ui.diary_history

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogDiaryHistoryBinding
import com.nexters.moss.ui.diary_history.adapter.DiaryHistoryAdapter
import kotlinx.android.synthetic.main.dialog_diary_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryHistoryDialog : BaseDialog<DialogDiaryHistoryBinding>() {

    private val dateList = arrayListOf<String>(
        "2020.01.05",
        "2020.05.09",
        "2222.22.22",
        "3333.33.33",
        "4444.44.44",
        "5555.55.55",
        "6666.66.66",
        "7777.77.77"
    )

    override val vm: DiaryHistoryViewModel by viewModel()
    override fun getLayoutRes() = R.layout.dialog_diary_history
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun getDialogWidth() = 400
    override fun getDialogHeight() = 550

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        val recyclerAdapter = DiaryHistoryAdapter(dateList)
        val recyclerManager = LinearLayoutManager(context!!)

        layout_history_recycler_view.apply {
            adapter = recyclerAdapter
            layoutManager = recyclerManager
            setHasFixedSize(false)
        }
    }
}
