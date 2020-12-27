package com.example.bottomnavview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.bottomnavview.BottomNavViewExt.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.first,
            R.navigation.second,
        )

        val controller = bottom_nav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        controller.observe(this,{
            setupActionBarWithNavController(this,it)
        })

        currentNavController = controller
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}