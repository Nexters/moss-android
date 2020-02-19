package com.nexters.moss.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.CakeModel
import com.nexters.moss.repository.CakeRepository
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch


class SendViewModel(
    private val cakeRepo: CakeRepository,
    private val habitRepo: HabitRepository
) : ViewModel() {
    private val _cakeList = MutableLiveData<ArrayList<CakeModel>>()
    val cakeList: LiveData<ArrayList<CakeModel>> get() = _cakeList

    private val _isEditEnable = MutableLiveData<Boolean>().apply { value = false }
    val isEditEnable: LiveData<Boolean> get() = _isEditEnable

    private val _isVisible = MutableLiveData<Boolean>().apply{ value = true }
    val isVisible : LiveData<Boolean> get() = _isVisible

    private val _cakeImage = MutableLiveData<Int>()
    val cakeImage : LiveData<Int> get() = _cakeImage

    private val _exit = MutableLiveData<Boolean>().apply{ value = false }
    val exit : LiveData<Boolean> get() = _exit

    private val _main = MutableLiveData<Boolean>().apply{ value = false }
    val main : LiveData<Boolean> get() = _main

    private val _toYou = MutableLiveData<String>()
    val toYou: LiveData<String> get() = _toYou

    private val _cakeName = MutableLiveData<String>()
    val cakeName: LiveData<String> get() = _cakeName

    val note = MutableLiveData<String>("")

    private var habikeryToken = ""
    private var categoryId = 0
    private var createCategoryId = 0
    private var isAddHabit = false

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

    fun exit(){
        _exit.value = true
    }

    fun sendCake(){
        viewModelScope.launch {
            val response = cakeRepo.sendCake(habikeryToken, categoryId + 1, note.value ?: "화이팅")
            DLog.d(response.toString())

            if (isAddHabit) {
                val response2 = habitRepo.createHabit(habikeryToken, createCategoryId)
                DLog.d(response2.toString())
            }
        }
    }

    fun changeString(toYou: String) {
        _toYou.value = toYou
    }

    fun changeCakeName(cakeName : String){
        _cakeName.value = cakeName
    }

    fun setCategoryId(position: Int) {
        categoryId = position
    }

    fun setCreateCategoryId(position: Int) {
        createCategoryId = position
    }

    fun setHabikeryToken(token: String) {
        habikeryToken = token
    }

    fun setAddHabit(enabled: Boolean) {
        isAddHabit = enabled
    }
}
