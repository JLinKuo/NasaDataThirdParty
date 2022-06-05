package com.example.nasadata.view.nasa_data_container_view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    parentFragment: Fragment
) : FragmentStateAdapter(parentFragment) {

    override fun createFragment(position: Int) = when(position % 2) {
        0 -> AllFragment()
        else -> FavorFragment()
    }

    override fun getItemCount() = 2
}