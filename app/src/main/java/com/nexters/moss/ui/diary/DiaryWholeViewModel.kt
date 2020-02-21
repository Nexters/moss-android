package com.nexters.moss.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.model.DiaryModel
import com.nexters.moss.model.HistoryModel
import com.nexters.moss.repository.DiaryRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class DiaryWholeViewModel(private val diaryRepo: DiaryRepository) : ViewModel() {

    private val _itemList = MutableLiveData<HistoryModel>()
    val itemList : LiveData<HistoryModel> get() = _itemList

    private val _cakeList = MutableLiveData<ArrayList<DiaryModel>>(ArrayList())
    val cakeList: LiveData<ArrayList<DiaryModel>> get() = _cakeList

    fun getCakeHistory(categoryId : Int, habikeryToken : String){
        viewModelScope.launch {
            _itemList.value = diaryRepo.getCakeHistory(habikeryToken, categoryId).data

            DLog.d(itemList.value.toString())
        }
    }

    fun setCakeList(habikeryToken: String) {
        viewModelScope.launch {
            _cakeList.value = diaryRepo.getWholeCakeDiary(habikeryToken).data as ArrayList

            //DLog.d("정보정보"+_cakeList.value.toString())
        }
    }
}