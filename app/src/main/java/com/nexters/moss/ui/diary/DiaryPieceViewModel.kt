package com.nexters.moss.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.DiaryCakeModel
import com.nexters.moss.model.DiaryModel
import com.nexters.moss.repository.DiaryRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class DiaryPieceViewModel(private val diaryRepo: DiaryRepository) : ViewModel() {

    private val _cakeList = MutableLiveData<List<DiaryModel>>(ArrayList())
    val cakeList: LiveData<List<DiaryModel>> get() = _cakeList

    fun setCakeList(habikeryToken: String) {
        viewModelScope.launch {
            val response =  diaryRepo.getPieceOfCakeDiary(habikeryToken).data as ArrayList
            val itemList : ArrayList<DiaryModel> = ArrayList()

            for (item in response){
                if (item.count !=0){
                    itemList.add(item)
                }
            }

            _cakeList.value = itemList

            //DLog.d("정보정보"+_cakeList.value.toString())

        }
    }
}