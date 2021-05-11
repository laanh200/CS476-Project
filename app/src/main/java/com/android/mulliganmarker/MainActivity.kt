package com.android.mulliganmarker

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    //Navigation view
    private lateinit var navView: NavigationView

    //Drawer Layout
    private lateinit var drawer:DrawerLayout

    private lateinit var bottomMenu: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //hide the Back arrow of the main activity to the welcome screen
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        val homeFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        //If the fragment container is empty
        if(homeFragment == null){
            //Create a fragment of the home fragment
            val newFragment = HomeFragment()
            //Add the newly created fragment to the fragment container
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, newFragment).commit()
        }

        bottomMenu = findViewById(R.id.bottomNav)
        bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {

                }
                R.id.add_new -> {
                    val bottomSheetFragment = BottomSheetFragment()
                    bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialogMenu")
                }
            }
            true
        }
    }




}