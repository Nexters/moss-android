package com.nexters.moss.ui.diary

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseFragment
import com.nexters.moss.databinding.FragmentPieceBinding
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.ui.diary.adapter.DiaryPieceRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_piece.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryPieceFragment : BaseFragment<FragmentPieceBinding>() {

    private val cakeList = arrayListOf<DiaryCakeModel>(
        DiaryCakeModel("클리어1", "야호야호", "녹차케이크"),
        DiaryCakeModel("클리어2", "메롱메롱", "딸기케이크"),
        DiaryCakeModel("클리어3", "냠냠냠냠", "초코케이크"),
        DiaryCakeModel("클리어4", "쭈욱쭈욱", "치즈케이크")
    )

    override val vm: DiaryPieceViewModel by viewModel()
    override fun getLayoutRes(): Int = R.layout.fragment_piece
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.setCakeList(cakeList)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerAdapter = DiaryPieceRecyclerAdapter(cakeList)
        val recyclerManager = LinearLayoutManager(context!!)

        with(layout_diary_piece_recycler) {
            adapter = recyclerAdapter
            layoutManager = recyclerManager
            setHasFixedSize(false)
        }
    }
}