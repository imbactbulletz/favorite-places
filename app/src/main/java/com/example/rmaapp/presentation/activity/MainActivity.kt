package com.example.rmaapp.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rmaapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCardListeners()
    }

    private fun initCardListeners() {
        addLocationCard.setOnClickListener {
            launchActivity(AddLocationActivity::class.java)
        }

        trackHistoryCard.setOnClickListener {
            launchActivity(TrackHistoryActivity::class.java)
        }
    }

    private fun launchActivity(clazz: Class<out AppCompatActivity>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}
