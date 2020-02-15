package com.nexters.moss.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.model.CakeModel


class SendViewModel : ViewModel() {
    private val _cakeList = MutableLiveData<ArrayList<CakeModel>>()
    val cakeList: LiveData<ArrayList<CakeModel>> get() = _cakeList

    private val _isEditEnable = MutableLiveData<Boolean>().apply { value = false }
    val isEditEnable: LiveData<Boolean> get() = _isEditEnable

    private val _exit = MutableLiveData<Boolean>().apply{ value = false }
    val exit : LiveData<Boolean> get() = _exit

    private val _main = MutableLiveData<Boolean>().apply{ value = false }
    val main : LiveData<Boolean> get() = _main


    fun setCakeList(list: ArrayList<CakeModel>) {
        _cakeList.value = list
    }

    fun isTextLengthEnough(enough: Boolean) {
        _isEditEnable.value = enough
    }

    fun exit(){
        _exit.value = true
    }

    fun goMain(){
        _main.value = true
    }

}
