package com.nexters.moss.ui.formation_habit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexters.moss.constant.HabitListConstant
import com.nexters.moss.repository.HabitRepository
import com.nexters.moss.utils.CategoryState

class FormationHabitViewModel(private val habitRepo: HabitRepository) : ViewModel() {
    private val _selectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = _selectedItem

    private val _isMakeHabit = MutableLiveData<Boolean>()
    val isMakeHabit: LiveData<Boolean> get() = _isMakeHabit

    private val _isClose = MutableLiveData<Boolean>()
    val isClose: LiveData<Boolean> get() = _isClose

    private val _existToastValue = MutableLiveData<String>()
    val existToastValue: LiveData<String> get() = _existToastValue

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

    fun getSelectedItem(): Int {
        val list = HabitListConstant.values()
        var position = 0

        for (i in list.indices) {
            if (list[i].getHabitName() == selectedItem.value) {
                position = i
                break
            }
        }

        return position + 1
    }


    fun makeHabit() {
        val selectedId = getSelectedItem() - 1
        if (CategoryState.getCategoryState()[selectedId]) {
            _existToastValue.value = "이미 있는 습관이에요!"
        } else {
            _isMakeHabit.value = true
            _isMakeHabit.value = false
        }

    }

    fun closeDisplay() {
        _isClose.value = true
    }
}