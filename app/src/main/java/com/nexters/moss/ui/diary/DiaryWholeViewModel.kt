package com.nexters.moss.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.model.HistoryModel
import com.nexters.moss.repository.DiaryRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class DiaryWholeViewModel(private val diaryRepo: DiaryRepository) : ViewModel() {

    private val _cakeList = MutableLiveData<ArrayList<DiaryCakeModel>>()
    val cakeList: LiveData<ArrayList<DiaryCakeModel>> get() = _cakeList

    private val _itemList = MutableLiveData<HistoryModel>()
    val itemList : LiveData<HistoryModel> get() = _itemList

    fun setCakeList(list: ArrayList<DiaryCakeModel>) {
        _cakeList.value = list
    }

    fun getCakeHistory(categoryId : Int, habikeryToken : String){
        viewModelScope.launch {
            _itemList.value = diaryRepo.getCakeHistory(habikeryToken, categoryId).data

            DLog.d(itemList.value.toString())
        }
    }
}