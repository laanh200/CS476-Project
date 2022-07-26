package com.android.mulliganmarker

import android.os.Bundle
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.android.mulliganmarker.databinding.ActivityMainBinding
import com.android.mulliganmarker.model.Round

import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), BottomSheetFragment.Callbacks, NewPlayerFragment.CallBacks, NewRoundFragment.Callbacks, NewScorecardFragment.Callbacks, RoundHistoryListFragment.Callbacks, ScoreKeepingFragment.Callbacks {

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
            /*
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()

             */
            replaceCurrentFragment(fragment)
        }

        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.player_list ->{
                    drawer.closeDrawer(GravityCompat.START)

                    val fragment = PlayerListFragment()
                    replaceCurrentFragment(fragment)
                }
                R.id.round_history ->{
                    drawer.closeDrawer(GravityCompat.START)
                    val fragment = RoundHistoryListFragment()
                    replaceCurrentFragment(fragment)
                }
                R.id.statistics ->{
                    drawer.closeDrawer(GravityCompat.START)
                    val fragment = StatisticsFragment()
                    replaceCurrentFragment(fragment)
                }
            }
            true
        }


        bottomMenu = findViewById(R.id.bottomNav)
        bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    val fragment = HomeFragment()
                    replaceCurrentFragment(fragment)
                }
                R.id.add_new -> {
                    val bottomSheetFragment = BottomSheetFragment()
                    bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialogMenu")
                }
            }
            true
        }
    }

    private fun replaceCurrentFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in, R.anim.fade_out, R.anim.fade_in,R.anim.slide_out
                )
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // Callback functions

    override fun onNewPlayer() {
        val fragment = NewPlayerFragment()

        supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in, R.anim.fade_out, R.anim.fade_in,R.anim.slide_out
                )
            .remove(supportFragmentManager.findFragmentByTag("BottomSheetDialogMenu")!!)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onPlayerAdded() {
        val fragment = HomeFragment()
        replaceCurrentFragment(fragment)
    }

    override fun onNewRound() {
        val fragment = NewRoundFragment()

        supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(
                R.anim.slide_in, R.anim.fade_out, R.anim.fade_in,R.anim.slide_out
            )
            .remove(supportFragmentManager.findFragmentByTag("BottomSheetDialogMenu")!!)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onRoundStarted(round: Round?) {
        val fragment = NewScorecardFragment(round)
        replaceCurrentFragment(fragment)
    }

    override fun onScorecardsCreated(round: Round?) {
        val fragment = ScoreKeepingFragment(round)
        replaceCurrentFragment(fragment)
    }

    override fun onRoundSelected(round: Round?) {
        if(round!!.hasScorecards) {
            val fragment = ScoreKeepingFragment(round)
            replaceCurrentFragment(fragment)
        }
        else {
            val fragment = NewScorecardFragment(round)
            replaceCurrentFragment(fragment)
        }
    }

    override fun onRoundCompleted() {
        val fragment = HomeFragment()
        replaceCurrentFragment(fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Return true if the menu item is being selected
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


