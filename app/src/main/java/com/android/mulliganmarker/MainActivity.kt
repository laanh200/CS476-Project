package com.android.mulliganmarker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide the Back arrow of the main activity to the welcome screen
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }
}