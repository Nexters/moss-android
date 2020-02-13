package com.nexters.moss.ui.receive_dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceiveDialogViewModel : ViewModel() {

    private val _submit = MutableLiveData<Boolean>().apply { value = false }
    val submit: LiveData<Boolean> get() = _submit

    private val _cancel = MutableLiveData<Boolean>().apply { value = false }
    val cancel: LiveData<Boolean> get() = _cancel

    fun submit() {
        _submit.value = true
    }

    fun cancel(){
        _cancel.value = true
    }
}