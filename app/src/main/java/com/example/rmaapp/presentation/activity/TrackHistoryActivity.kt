package com.example.rmaapp.presentation.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rmaapp.R
import com.example.rmaapp.presentation.adapter.viewpager.TrackHistoryViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_track_history.*

class TrackHistoryActivity : AppCompatActivity(R.layout.activity_track_history) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        viewPager.adapter = TrackHistoryViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Map"
                1 -> tab.text = "List of Locations"
            }
        }.attach()
    }
}