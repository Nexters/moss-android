package com.nexters.moss.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.repository.DiaryRepository

class DiaryWholeViewModel(private val diaryRepo: DiaryRepository) : ViewModel() {

    private val _cakeList = MutableLiveData<ArrayList<DiaryCakeModel>>()
    val cakeList: LiveData<ArrayList<DiaryCakeModel>> get() = _cakeList

    fun setCakeList(list: ArrayList<DiaryCakeModel>) {
        _cakeList.value = list
    }
}