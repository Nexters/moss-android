package com.nexters.moss.ui.formation_habit.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.util.TypedValue

class FormationHabitListDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val horizontalValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            12f,
            view.resources.displayMetrics
        ).toInt()

        val verticalValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            12f,
            view.resources.displayMetrics
        ).toInt()

        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.set(0, 0, horizontalValue, verticalValue)
        } else {
            outRect.set(0, 0, 0, verticalValue)
        }
    }
}

