package com.nexters.moss.ui.dialog_report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiveDialogViewModel : ViewModel() {

    private val _submit = MutableLiveData<Boolean>().apply { value = false }
    val submit: LiveData<Boolean> get() = _submit

    private val _cancel = MutableLiveData<Boolean>().apply { value = false }
    val cancel: LiveData<Boolean> get() = _cancel

    private val _report = MutableLiveData<Int>().apply { value = 0 }
    val report: LiveData<Int> get() = _report

    private val _firstReport = MutableLiveData<Boolean>(true)
    val firstReport: LiveData<Boolean> get() = _firstReport

    val reason = MutableLiveData<String>()

    fun submit() {
        _submit.value = true
    }

    fun cancel() {
        _cancel.value = true
    }

    fun reportSelected(reportNum: Int) {
        _report.value = reportNum
    }

    fun itemSelected() {
        _firstReport.value = false
    }
}