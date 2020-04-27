package com.example.unsplashphotos.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PhotosRecyclerViewItemDecroator(
        var firstCellMarginTop: Int,
        var cellMarginBottom: Int,
        var cellMarginLeft: Int,
        var cellMarginRight: Int
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = firstCellMarginTop
                }
                left = cellMarginLeft
                right = cellMarginRight
                bottom = cellMarginBottom
            }
        }

    }