package com.nexters.moss.ui.formation_habit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.repository.UserRepository
import com.nexters.moss.utils.DLog
import kotlinx.coroutines.launch

class FormationHabitViewModel(private val habitRepo: HabitRepository) : ViewModel() {
    private val _selectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = _selectedItem

    private val _isMakeHabit = MutableLiveData<Boolean>()
    val isMakeHabit: LiveData<Boolean> get() = _isMakeHabit

    private val _isClose = MutableLiveData<Boolean>()
    val isClose: LiveData<Boolean> get() = _isClose

    val formationHabitList by lazy {
        val values = HabitListConstant.values()

        ArrayList<String>().apply {
            for (value in values) {
                add(value.getHabitName())
            }
        }
    }

    fun selectItem(item: String) {
        _selectedItem.value = item
    }

    fun makeHabit() {
        _isMakeHabit.value = true
        _isMakeHabit.value = false

        viewModelScope.launch {
            DLog.d(habitRepo.createHabit(1, 1).toString())
        }
    }

    fun closeDisplay() {
        _isClose.value = true
    }
}