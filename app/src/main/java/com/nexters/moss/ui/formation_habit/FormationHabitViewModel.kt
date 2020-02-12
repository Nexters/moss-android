package com.nexters.moss.ui.formation_habit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormationHabitViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<String>()
    val selectedItem: LiveData<String> get() = _selectedItem

    private val _isMakeHabit = MutableLiveData<Boolean>()
    val isMakeHabit: LiveData<Boolean> get() = _isMakeHabit

    val formationHabitList = arrayListOf(
        "물마시기", "스트레칭", "명상", "산책",
        "뉴스보기", "아침식사", "일기쓰기", "책읽기"
    )

    fun selectItem(item: String) {
        _selectedItem.value = item
    }

    fun makeHabit() {
        _isMakeHabit.value = true
        _isMakeHabit.value = false
    }
}