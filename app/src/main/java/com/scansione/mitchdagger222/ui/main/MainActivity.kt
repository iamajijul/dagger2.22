package com.scansione.mitchdagger222.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.scansione.mitchdagger222.BaseActivity
import com.scansione.mitchdagger222.R
import com.scansione.mitchdagger222.ui.main.post.PostFragment
import com.scansione.mitchdagger222.ui.main.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
      val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawer_layout)
        NavigationUI.setupWithNavController(nav_view,navController)
        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
            }

            android.R.id.home->{
                if(drawer_layout.isDrawerOpen(GravityCompat.START)){
                    drawer_layout.closeDrawer(GravityCompat.START)
                    return true
                }

                return false

        }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_profile -> {

                val navOption = NavOptions.Builder()
                    .setPopUpTo(R.id.main,true)
                    .build()
                Navigation.findNavController(this,R.id.nav_host_fragment)
                    .navigate(R.id.profile_screen,null,navOption)
            }

            R.id.nav_posts -> {
                if(isValidDestination(R.id.nav_posts))
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.post_screen)

            }
        }

        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun isValidDestination(id : Int):Boolean{
       return id!=Navigation.findNavController(this,R.id.nav_host_fragment).currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),drawer_layout)
    }
}