package com.nexters.moss.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiveViewModel : ViewModel() {

    private val _report = MutableLiveData<Boolean>().apply{ value = false}
    val report : LiveData<Boolean> get() = _report

    fun clickBtnReport() {
        _report.value = true
    }

    fun setBtnReportStatus(isClicked : Boolean){
        _report.value = isClicked
    }
}
