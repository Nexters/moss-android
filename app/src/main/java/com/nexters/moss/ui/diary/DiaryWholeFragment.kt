package com.nexters.moss.ui.diary

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexters.moss.R
import com.nexters.moss._base.BaseFragment
import com.nexters.moss.constant.SharedPreferenceConstant
import com.nexters.moss.databinding.FragmentWholeBinding
import com.nexters.moss.extension.getUserSharedPreference
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.model.DiaryModel
import com.nexters.moss.ui.diary.adapter.DiaryWholeRecyclerAdapter
import com.nexters.moss.ui.diary_history.DiaryHistoryDialog
import kotlinx.android.synthetic.main.fragment_whole.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryWholeFragment : BaseFragment<FragmentWholeBinding>() {

    private val recyclerAdapter by lazy {
        DiaryWholeRecyclerAdapter(vm.cakeList.value!!)
    }

    override val vm: DiaryWholeViewModel by viewModel()
    override fun getLayoutRes(): Int = R.layout.fragment_whole
    override fun setupBinding() {
        binding.vm = vm
    }

    lateinit var habikeryToken: String

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
        val recyclerManager = LinearLayoutManager(context!!)

        layout_diary_whole_recycler.apply {
            adapter = recyclerAdapter.apply {
                setOnItemClickListener { position ->

                    val categoryId = position + 1
                    vm.getCakeHistory(categoryId, habikeryToken)

                    vm.itemList.observe(viewLifecycleOwner, Observer {
                        val item = vm.itemList.value

                        DiaryHistoryDialog().apply{
                            val bundle = Bundle()
                            bundle.putParcelable(KEY, item)
                            arguments = bundle
                        }.run {
                            show(this@DiaryWholeFragment.childFragmentManager, KEY)
                        }
                    })
                }
            }

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

    companion object {
        const val KEY="key"
    }

}