package com.nexters.moss.ui.diary_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.HistoryModel
import com.nexters.moss.repository.DiaryRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class DiaryHistoryViewModel(private val diaryRepo: DiaryRepository) : ViewModel() {

    private val _exit = MutableLiveData<Boolean>().apply { value = false }
    val exit: LiveData<Boolean> get() = _exit

    private val _itemList = MutableLiveData<HistoryModel>()
    val itemList : LiveData<HistoryModel> get() = _itemList

    fun exit() {
        _exit.value = true
    }

    fun getCakeHistory(categoryId : Int, habikeryToken : String){
        viewModelScope.launch {
            _itemList.value = diaryRepo.getCakeHistory(habikeryToken, categoryId).data

            DLog.d(itemList.value.toString())
        }
    }
}

