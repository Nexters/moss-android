package com.nexters.moss.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.utils.DLog

class MainViewModel : ViewModel() {
    private val _isOpenDrawer = MutableLiveData<Boolean>().apply { value = false }
    val isOpenDrawer: LiveData<Boolean> get() = _isOpenDrawer

    fun openDrawer() {
        DLog.d("open drawer")
        _isOpenDrawer.value = true
    }
}