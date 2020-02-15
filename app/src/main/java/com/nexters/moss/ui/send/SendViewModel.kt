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

    private val _isVisible = MutableLiveData<Boolean>().apply{ value = true }
    val isVisible : LiveData<Boolean> get() = _isVisible

    private val _cakeImage = MutableLiveData<Int>()
    val cakeImage : LiveData<Int> get() = _cakeImage

    fun setCakeList(list: ArrayList<CakeModel>) {
        _cakeList.value = list
    }

    fun isTextLengthEnough(enough: Boolean) {
        _isEditEnable.value = enough
    }

    fun isBtnVisible(visible : Boolean){
        _isVisible.value = visible
    }

    fun changeCakeImage(image : Int){
        _cakeImage.value = image
    }
}
