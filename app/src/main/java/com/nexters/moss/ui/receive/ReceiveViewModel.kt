package com.nexters.moss.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiveViewModel : ViewModel() {

    private val _report = MutableLiveData<Boolean>().apply{ value = false}
    val report : LiveData<Boolean> get() = _report

    private val _exit = MutableLiveData<Boolean>().apply{ value = false }
    val exit : LiveData<Boolean> get() = _exit

    private val _diary = MutableLiveData<Boolean>().apply{ value = false }
    val diary : LiveData<Boolean> get() = _diary

    private val _send = MutableLiveData<Boolean>().apply{ value = false }
    val send : LiveData<Boolean> get() = _send


    fun clickBtnReport() {
        _report.value = true
    }

    fun exit(){
        _exit.value = true
    }

    fun goDiary(){
        _diary.value = true
    }

    fun goSend(){
        _send.value = true
    }
}
