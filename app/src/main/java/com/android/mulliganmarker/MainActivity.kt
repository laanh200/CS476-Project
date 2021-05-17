package com.android.mulliganmarker

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.mulliganmarker.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), BottomSheetFragment.Callbacks, NewPlayerFragment.CallBacks {

    //Navigation view
    private lateinit var navView: NavigationView

    //Bottom navigation
    private lateinit var bottomMenu: BottomNavigationView

    //Action bar drawer toggle type
    private lateinit var toggle: ActionBarDrawerToggle

    //Drawer Layout
    private lateinit var drawer: DrawerLayout


    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)


        //Linking the drawer layout in the main activity xml
        drawer= findViewById(R.id.drawer_Layout)

        //Linking the action bar toggle layout between the drawer view and main activity
        toggle = ActionBarDrawerToggle(this,drawer, R.string.open, R.string.close)

        //connect toggle with drawer layout
        drawer.addDrawerListener(toggle)

        //Sync the state of the toggle and ready to be used
        toggle.syncState()

        //hide the Back arrow of the main activity to the welcome screen
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        //If the fragment container is empty
        if(currentFragment == null){
            //Create a fragment of the home fragment
           val fragment = HomeFragment()
            //Add the newly created fragment to the fragment container
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.player_list ->{
                    drawer.closeDrawer(GravityCompat.START)
                    onPlayerList()
                }
                R.id.round_history ->{
                    drawer.closeDrawer(GravityCompat.START)
                }
            }
            true
        }


        bottomMenu = findViewById(R.id.bottomNav)
        bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    val fragment = HomeFragment()
                    //Add the newly created fragment to the fragment container
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit()
                }
                R.id.add_new -> {
                    val bottomSheetFragment = BottomSheetFragment()
                    bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialogMenu")
                }
            }
            true
        }
    }

    override fun onNewPlayer() {
        val fragment = NewPlayerFragment()
        //Replace the newly created fragment to the fragment container
        supportFragmentManager
                .beginTransaction()
                .remove(supportFragmentManager.findFragmentByTag("BottomSheetDialogMenu")!!)
                .replace(R.id.fragment_container, fragment)
                .commit()
    }
    override fun onHome(){
        val fragment = HomeFragment()
        //Add the newly created fragment to the fragment container
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }
    private fun onPlayerList(){
        val fragment = PlayerListFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }

    override fun onNewRound() {
        TODO("Not yet implemented")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Return true if the menu item is being selected
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


