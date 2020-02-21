package com.nexters.moss.utils

object CategoryState {
    private val categoryState = Array(8) {
        false
    }

    fun resetCategoryState() {
        for (i in categoryState.indices)
            categoryState[i] = false
    }

    fun setCategoryState(position: Int, isExist: Boolean) {
        categoryState[position] = isExist
        DLog.d("category State")
        for (state in categoryState) {
            DLog.d(state.toString())
        }
    }

    fun getCategoryState() = categoryState
}