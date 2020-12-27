package com.saddar.navigationcomponent.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.saddar.navigationcomponent.NavGraphDirections
import com.saddar.navigationcomponent.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dynamic Toolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_id) as NavHostFragment
        navController = navHostFragment.findNavController()

        //Configuration for top level destination with no back icon
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragment_Home, R.id.searchFragment2, R.id.addPostFragment, R.id.likeFragment, R.id.profileFragment),
            drawer_layout
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //For Bottom Navigation
        bottom_nav.setupWithNavController(navController)
        //Drawer Latout
        nav_view.setupWithNavController(navController)
    }

    //For Navigation, appBarConfiguration for drawer layout
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //For Option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    //On option item click method
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (item.itemId == R.id.terms_condition) {
            val action =
                NavGraphDirections.actionGlobalTermsFragment()
            navController.navigate(action)
            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }
}