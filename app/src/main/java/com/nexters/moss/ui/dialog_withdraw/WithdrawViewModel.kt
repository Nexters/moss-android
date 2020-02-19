package com.nexters.moss.ui.dialog_withdraw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.repository.UserRepository
import kotlinx.coroutines.launch

class WithdrawViewModel(private val userRepo: UserRepository) : ViewModel() {
    private val _isWithdrawFinish = MutableLiveData<Boolean>(false)
    val isWithdrawFinish: LiveData<Boolean> get() = _isWithdrawFinish

    fun withdraw(habikeryToken: String) {
        viewModelScope.launch {
            userRepo.leave(habikeryToken)
            _isWithdrawFinish.value = true
        }
    }
}