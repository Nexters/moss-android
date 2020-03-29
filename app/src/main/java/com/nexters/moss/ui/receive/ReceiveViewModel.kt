package com.nexters.moss.ui.receive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.model.NewCakeModel

class ReceiveViewModel : ViewModel() {

    private val _report = MutableLiveData<Boolean>().apply{ value = false}
    val report : LiveData<Boolean> get() = _report

    private val _exit = MutableLiveData<Boolean>().apply{ value = false }
    val exit : LiveData<Boolean> get() = _exit

    private val _diary = MutableLiveData<Boolean>().apply{ value = false }
    val diary : LiveData<Boolean> get() = _diary

    private val _send = MutableLiveData<Boolean>().apply{ value = false }
    val send : LiveData<Boolean> get() = _send

    private val _nickname = MutableLiveData<String>("")
    val nickname: LiveData<String> get() = _nickname

    private val _note = MutableLiveData<String>("")
    val note: LiveData<String> get() = _note

    private val _cakeName = MutableLiveData<String>("")
    val cakeName: LiveData<String> get() = _cakeName

    private val _imagePath = MutableLiveData<String>("")
    val imagePath: LiveData<String> get() = _imagePath

    fun setNewCake(newCake: NewCakeModel) {
        newCake.let {
            _nickname.value = it.nickname
            _note.value = it.note
            _cakeName.value = it.cakeName
            _imagePath.value = it.imagePath
        }
    }

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
