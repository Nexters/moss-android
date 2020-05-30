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


            itemList.observe(viewLifecycleOwner, Observer {
                DiaryHistoryDialog().apply {
                    val bundle = Bundle()
                    bundle.putParcelable(KEY, it)
                    arguments = bundle
                }.run {
                    show(this@DiaryWholeFragment.childFragmentManager, KEY)
                }
            })
        }
    }

    private fun setRecyclerView() {
        val recyclerManager = LinearLayoutManager(context!!)

        layout_diary_whole_recycler.apply {
            adapter = recyclerAdapter.apply {
                setOnItemClickListener {

                    val list = vm.categoryList.value as ArrayList
                    val id = translateItem(list[it])

                    vm.getCakeHistory(id, habikeryToken)
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

    private fun translateItem(item: String): Int {
        return when (item) {
            "수박" -> 1
            "치즈" -> 2
            "생크림" -> 3
            "녹차" -> 4
            "커피" -> 5
            "사과" -> 6
            "밤" -> 7
            "아몬드" -> 8
            else -> 1
        }
    }

    companion object {
        const val KEY = "key"
    }

}