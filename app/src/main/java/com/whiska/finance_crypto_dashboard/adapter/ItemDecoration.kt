package com.whiska.finance_crypto_dashboard.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(private val space: Int, private val orientation: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if(orientation == LinearLayoutManager.VERTICAL) {
                if(parent.getChildAdapterPosition(view) == 0) {
                    top = space
                }
            } else {
                top = space
                right = space
            }
            bottom = space
        }
    }
}