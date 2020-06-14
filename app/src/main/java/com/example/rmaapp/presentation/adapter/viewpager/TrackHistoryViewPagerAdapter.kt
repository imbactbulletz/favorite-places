package com.example.rmaapp.presentation.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rmaapp.presentation.fragment.MapHistoryFragment

class TrackHistoryViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    companion object {
        private const val ITEM_COUNT = 2
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MapHistoryFragment()
            else -> Fragment()
        }
    }
}