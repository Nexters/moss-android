package com.nexters.moss.ui.diary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseFragment
import com.nexters.moss.databinding.FragmentWholeBinding
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.ui.diary.adapter.DiaryWholeRecyclerAdapter
import com.nexters.moss.ui.diary_history.DiaryHistoryDialog
import kotlinx.android.synthetic.main.fragment_whole.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryWholeFragment : BaseFragment<FragmentWholeBinding>() {

    private val cakeList = arrayListOf<DiaryCakeModel>(
        DiaryCakeModel("클리어9", "야호야호", "녹차케이크"),
        DiaryCakeModel("클리어8", "메롱메롱", "딸기케이크"),
        DiaryCakeModel("클리어7", "냠냠냠냠", "초코케이크")
    )

    override val vm : DiaryWholeViewModel by viewModel()
    override fun getLayoutRes(): Int = R.layout.fragment_whole
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.setCakeList(cakeList)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerAdapter = DiaryWholeRecyclerAdapter(cakeList)
        val recyclerManager = LinearLayoutManager(context!!)

        layout_diary_whole_recycler.apply {
            adapter = recyclerAdapter.apply{
                setOnItemClickListener {
                    //Toast.makeText(context, "click!!"+it, Toast.LENGTH_LONG).show()
                    DiaryHistoryDialog().show(childFragmentManager, "")
                }
            }
            layoutManager = recyclerManager
            setHasFixedSize(false)
        }
    }
}