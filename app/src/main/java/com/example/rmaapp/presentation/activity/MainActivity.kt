package com.example.rmaapp.presentation.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rmaapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DUMMY_REQUEST_CODE = 0
    }

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
        startActivityForResult(intent, DUMMY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            showSnackbar("Location succesfully saved!")
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG)
            .setAction("Close", null)
            .show()
    }
}
