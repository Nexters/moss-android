package com.nexters.moss.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.ui.diary.adapter.DiaryPieceRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_piece.*

class DiaryWholeFragment : Fragment() {

    private val cakeList = arrayListOf<DiaryCakeModel>(
        DiaryCakeModel("클리어9", "야호야호", "녹차케이크"),
        DiaryCakeModel("클리어8", "메롱메롱", "딸기케이크"),
        DiaryCakeModel("클리어7", "냠냠냠냠", "초코케이크")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_whole, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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