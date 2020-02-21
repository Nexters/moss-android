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

    private val _habit = MutableLiveData<String>(" ")
    val habit : LiveData<String> get() = _habit

    private val _description = MutableLiveData<String>(" ")
    val description : LiveData<String> get() = _description

    private val _cakeImage = MutableLiveData<String>(" ")
    val cakeImage : LiveData<String> get() = _cakeImage

    fun exit() {
        _exit.value = true
    }

    fun setTextHabit(text : String){
        _habit.value = text
    }

    fun setTextDescription(text : String){
        _description.value = text
    }

    fun changeCakeImage(image : String){
        _cakeImage.value = image
    }

}

