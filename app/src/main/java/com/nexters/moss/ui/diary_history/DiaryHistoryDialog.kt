package com.nexters.moss.ui.diary_history

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseDialog
import com.nexters.moss.databinding.DialogDiaryHistoryBinding
import com.nexters.moss.model.HistoryModel
import com.nexters.moss.ui.diary_history.adapter.DiaryHistoryAdapter
import com.nexters.moss.utils.DLog
import kotlinx.android.synthetic.main.dialog_diary_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryHistoryDialog : BaseDialog<DialogDiaryHistoryBinding>() {

    override val vm: DiaryHistoryViewModel by viewModel()

    var item: HistoryModel? = null

    override fun getLayoutRes() = R.layout.dialog_diary_history
    override fun setupBinding() {
        binding.vm = vm
    }

    lateinit var habikeryToken: String

    override fun getDialogWidth() = 370
    override fun getDialogHeight() = 560


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        item = arguments?.getParcelable(KEY)

        setChange()
        setRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        with(vm) {
            exit.observe(viewLifecycleOwner, Observer {
                if (it) {
                    dismiss()
                }
            })
        }
    }

    private fun setRecyclerView() {

        DLog.d("" + item!!.dates)

        val recyclerAdapter = DiaryHistoryAdapter(item!!.dates)
        val recyclerManager = LinearLayoutManager(context!!)

        layout_history_recycler_view.apply {
            adapter = recyclerAdapter
            layoutManager = recyclerManager
            setHasFixedSize(false)
        }
    }

    private fun setChange() {
        vm.changeCakeImage(item!!.imagePath)

        DLog.d(item!!.imagePath)
        vm.setTextHabit(item!!.habitName)
        vm.setTextDescription(item!!.description + " " + item!!.cakeName)
    }

    companion object {
        const val KEY = "key"
    }
}
