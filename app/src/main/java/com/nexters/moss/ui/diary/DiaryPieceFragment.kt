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

class DiaryPieceFragment : Fragment() {


    private val cakeList = arrayListOf<DiaryCakeModel>(
        DiaryCakeModel("클리어1", "야호야호", "녹차케이크"),
        DiaryCakeModel("클리어2", "메롱메롱", "딸기케이크"),
        DiaryCakeModel("클리어3", "냠냠냠냠", "초코케이크"),
        DiaryCakeModel("클리어4", "쭈욱쭈욱", "치즈케이크")
    )

    //lateinit var binding : FragmentPieceBinding
    //var vm : ViewModel = DiaryPieceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_piece_no, container,false)


//        binding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_piece, container, false
//        )
//
//        with(binding){
//            pieceVM = DiaryPieceFragment()
//            lifecycleOwner = this@DiaryPieceFragment
//        }
//
//       return binding.root
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