package com.nexters.moss.ui.send.adapter

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nexters.moss.utils.DLog

class SendListDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val horizontalStartEndValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            view.resources.displayMetrics
        ).toInt()

        val horizontalValue = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            16f,
            view.resources.displayMetrics
        ).toInt()

        when (parent.getChildLayoutPosition(view)) {
            0 -> outRect.set(horizontalStartEndValue, 0, horizontalValue, 0)
            state.itemCount - 1 -> outRect.set(0, 0, horizontalStartEndValue, 0)
            else -> outRect.set(0, 0, horizontalValue, 0)
        }
    }
}

