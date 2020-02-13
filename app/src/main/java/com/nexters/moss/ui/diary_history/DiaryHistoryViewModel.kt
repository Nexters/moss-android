package com.nexters.moss.ui.diary_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiaryHistoryViewModel : ViewModel() {

    private val _exit = MutableLiveData<Boolean>().apply{ value = false}
    val exit : LiveData<Boolean> get() = _exit

    fun exit(){
        _exit.value = true
    }
}