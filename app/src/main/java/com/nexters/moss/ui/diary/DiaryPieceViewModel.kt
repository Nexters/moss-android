package com.nexters.moss.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.repository.DiaryRepository
import kotlinx.coroutines.launch

class DiaryPieceViewModel(private val diaryRepo : DiaryRepository) : ViewModel() {

    private val _cakeList = MutableLiveData<ArrayList<DiaryCakeModel>>()
    val cakeList: LiveData<ArrayList<DiaryCakeModel>> get() = _cakeList

    fun setCakeList(habikeryToken : String) {
//        _cakeList.value = list
        viewModelScope.launch {
            val response = diaryRepo.getPieceOfCakeDiary(habikeryToken)

        }



    }

}