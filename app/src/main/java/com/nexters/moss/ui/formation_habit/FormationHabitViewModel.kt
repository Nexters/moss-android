package com.nexters.moss.ui.formation_habit

import androidx.lifecycle.ViewModel

class FormationHabitViewModel : ViewModel() {
    val formationHabitList = arrayListOf(
        "물마시기", "스트레칭", "명상", "산책",
        "뉴스보기", "아침식사", "일기쓰기", "책읽기"
    )
}