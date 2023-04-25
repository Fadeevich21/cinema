package com.example.cinema.movie_posters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MoviePostersDecoration(
    private val top: Int = 0,
    private val left: Int = 0,
    private val right: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = left
        outRect.right = right

//        val position: Int = parent.getChildLayoutPosition(view)
//        if (position != 0)
        outRect.top = top
    }
}