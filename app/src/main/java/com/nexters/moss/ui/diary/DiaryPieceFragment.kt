package com.nexters.moss.ui.diary

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseFragment
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.FragmentPieceBinding
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.model.DiaryModel
import com.nexters.moss.ui.diary.adapter.DiaryPieceRecyclerAdapter
import com.nexters.moss.utils.DLog
import kotlinx.android.synthetic.main.fragment_piece.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryPieceFragment : BaseFragment<FragmentPieceBinding>() {
    private val recyclerAdapter by lazy {
        DiaryPieceRecyclerAdapter(vm.cakeList.value!!)
    }

    lateinit var habikeryToken: String

    override val vm: DiaryPieceViewModel by viewModel()
    override fun getLayoutRes(): Int = R.layout.fragment_piece
    override fun setupBinding() {
        binding.vm = vm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHabikeryToken()
        vm.setCakeList(habikeryToken)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        with(vm) {
            cakeList.observe(viewLifecycleOwner, Observer {
                recyclerAdapter.refreshItemList(it as ArrayList<DiaryModel>)
            })
        }
    }

    private fun setRecyclerView() {
        DLog.d("정보정보"+vm.cakeList.value!!)

        val recyclerManager = LinearLayoutManager(context!!)

        with(layout_diary_piece_recycler) {
            adapter = recyclerAdapter
            layoutManager = recyclerManager
            setHasFixedSize(false)
        }
    }

    private fun getHabikeryToken() {
        activity?.getUserSharedPreference()?.let {
            habikeryToken = it.getString(
                SharedPreferenceConstant.HABIKERY_TOKEN.getValue(),
                null
            ) ?: return
        }
    }
}