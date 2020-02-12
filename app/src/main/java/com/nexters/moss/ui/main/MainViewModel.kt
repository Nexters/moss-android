package com.nexters.moss.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.utils.DLog

class MainViewModel : ViewModel() {
    private val _isOpenDrawer = MutableLiveData<Boolean>().apply { value = false }
    val isOpenDrawer: LiveData<Boolean> get() = _isOpenDrawer

    private val _isWithDraw = MutableLiveData<Boolean>(false)
    val isWithDraw: LiveData<Boolean> get() = _isWithDraw

    private val _isLogout = MutableLiveData<Boolean>(false)
    val isLogout: LiveData<Boolean> get() = _isLogout

    private val _isEditMode = MutableLiveData<Boolean>(false)
    val isEditMode: LiveData<Boolean> get() = _isEditMode

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

}
