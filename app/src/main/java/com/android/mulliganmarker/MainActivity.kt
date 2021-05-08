package com.android.mulliganmarker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    //Navigation view
    private lateinit var navView: NavigationView

    //Drawer Layout
    private lateinit var drawer:DrawerLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //hide the Back arrow of the main activity to the welcome screen
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

    }



}