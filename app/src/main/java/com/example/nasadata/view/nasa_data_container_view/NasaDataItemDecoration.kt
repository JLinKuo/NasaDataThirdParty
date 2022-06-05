package com.example.nasadata.view.nasa_data_container_view

import android.app.Activity
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nasadata.R

class NasaDataItemDecoration(private val activity: Activity): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        parent.adapter?.let {
            outRect.top = activity.resources.getDimension(R.dimen.nasa_data_item_decoration_separate_space).toInt()
            outRect.bottom = activity.resources.getDimension(R.dimen.nasa_data_item_decoration_separate_space).toInt()
            outRect.left = activity.resources.getDimension(R.dimen.nasa_data_item_decoration_separate_space).toInt()
            outRect.right = activity.resources.getDimension(R.dimen.nasa_data_item_decoration_separate_space).toInt()
        }
    }
}