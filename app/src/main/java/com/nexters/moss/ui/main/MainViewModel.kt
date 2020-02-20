package com.nexters.moss.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.model.HabitModel
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepo: UserRepository,
    private val habitRepo: HabitRepository
) : ViewModel() {
    private val _isOpenDrawer = MutableLiveData<Boolean>().apply { value = false }
    val isOpenDrawer: LiveData<Boolean> get() = _isOpenDrawer

    private val _isWithDraw = MutableLiveData<Boolean>(false)
    val isWithDraw: LiveData<Boolean> get() = _isWithDraw

    private val _isLogout = MutableLiveData<Boolean>(false)
    val isLogout: LiveData<Boolean> get() = _isLogout

    private val _isEditMode = MutableLiveData<Boolean>(false)
    val isEditMode: LiveData<Boolean> get() = _isEditMode

    private val _intentDiary = MutableLiveData<Boolean>(false)
    val intentDiary: LiveData<Boolean> get() = _intentDiary

    private val _intentSend = MutableLiveData<Boolean>(false)
    val intentSend: LiveData<Boolean> get() = _intentSend

    private val _nickname = MutableLiveData<String>("미확인")
    val nickname: LiveData<String> get() = _nickname

    private val _itemList = MutableLiveData<ArrayList<HabitModel>>(ArrayList())
    val itemList: LiveData<ArrayList<HabitModel>> get() = _itemList

    fun openDrawer() {
        DLog.d("open drawer")
        _isOpenDrawer.value = true
    }

    fun setDrawerState(isOpened: Boolean) {
        _isOpenDrawer.value = isOpened
    }

    fun withDraw() {
        setDrawerState(false)
        _isWithDraw.value = true
        _isWithDraw.value = false
    }

    fun logout() {
        setDrawerState(false)
        _isLogout.value = true
        _isLogout.value = false
    }

    fun setEditMode(enabled: Boolean) {
        DLog.d("call $enabled")
        _isEditMode.value = enabled
    }

    fun openDiary() {
        _intentDiary.value = true
    }

    fun openSend() {
        _intentSend.value = true
    }

    fun setNickname(habikeryToken: String) {
        viewModelScope.launch {
            val response = userRepo.getUserInfo(habikeryToken)

            _nickname.value = response.nickname ?: "unknown"
        }
    }

    fun refreshItemList2(itemList : ArrayList<String>) {
//        _itemList.value = itemList
    }

    fun refreshItemList(habikeryToken: String) {
        viewModelScope.launch {
            _itemList.value = habitRepo.getHabit(habikeryToken).data as ArrayList
        }
    }
}
