package com.nexters.moss.ui.dialog_remove_habit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class RemoveHabitViewModel(private val habitRepo: HabitRepository) : ViewModel() {
    private val _isFinishDelete = MutableLiveData<Boolean>()
    val isFinishDelete: LiveData<Boolean> get() = _isFinishDelete

    fun deleteHabit(habikeryToken: String, categoryId: Int) {
        viewModelScope.launch {
            val response = habitRepo.deleteHabit(habikeryToken, categoryId)
            DLog.d(response.toString())

            _isFinishDelete.value = true
        }
    }
}