package com.walidkh.android.myfavcars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.walidkh.android.myfavcars.cars.CarsListFragmentDirections
import com.walidkh.android.myfavcars.databinding.ActivityMainBinding
import com.walidkh.android.myfavcars.details.AddCarFragment
import com.walidkh.android.myfavcars.details.AddCarFragmentDirections
import com.walidkh.android.myfavcars.preferences.PreferencesProvider

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var navController: NavController
    lateinit var preferencesProvider: PreferencesProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        initToolbar(binding)

        initPreferences()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        // show and hide the app bar depending on the screen
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.carsListFragment -> {
                    binding.appBar.visibility = VISIBLE
                    binding.toolbar.title = "Cars list"
                }
                R.id.addCarFragment -> {
                    binding.appBar.visibility = VISIBLE
                    binding.toolbar.title = "Add new car"
                }
                else -> binding.appBar.visibility = GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.popup_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logoutId -> {
                // popup to login screen
                logout()

                navigateBack()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateBack() {
        when(val id = navController.currentDestination?.id) {
            R.id.carsListFragment ->
                navController.navigate(
                    CarsListFragmentDirections.actionCarsListFragmentToLoginFragment()
                )
            R.id.addCarFragment ->
                navController.navigate(
                    AddCarFragmentDirections.actionAddCarFragmentToLoginFragment2()
                )
            else -> Log.i(TAG, "Destination not found with id $id")
        }
    }

    private fun initToolbar(binding: ActivityMainBinding) {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    private fun initPreferences() {
        preferencesProvider = PreferencesProvider(applicationContext)
    }

    private fun logout() {
        preferencesProvider.isUserConnected(false)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
