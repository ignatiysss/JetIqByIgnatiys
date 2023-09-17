package com.example.jetiq

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.jetiq.databinding.ActivityMainBinding
import com.example.jetiq.fragment.HomeFragment
import com.example.jetiq.fragment.LogOfSuccessFragment
import com.example.jetiq.fragment.MaterialFragment
import com.example.jetiq.fragment.MessageFragment
import com.example.jetiq.fragment.ScheduleMainFragment
import com.example.jetiq.fragment.SettingsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //TODO add direction (gmaps) for schedule
    lateinit var fab: FloatingActionButton
    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    public lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toggle.syncState()
        setHamburgerColorBasedOnTheme()

        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer_content_view, HomeFragment()).commit()

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            if (currentFragment() is MessageFragment) {

                // Change the click listener
                Toast.makeText(this, "Clicked in MessageFragment", Toast.LENGTH_SHORT).show()

            } else {
                // Do default action
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer_content_view, MessageFragment())
                    .addToBackStack(null).commit()
            }
        }

        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer_content_view, HomeFragment())
                        .addToBackStack(null).commit()
                    drawer.closeDrawers()
                    true
                }

                R.id.nav_schedule -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer_content_view, ScheduleMainFragment())
                        .addToBackStack(null).commit()
                    drawer.closeDrawers()
                    true
                }

                R.id.nav_material -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer_content_view, MaterialFragment())
                        .addToBackStack(null).commit()
                    drawer.closeDrawers()
                    true
                }

                R.id.nav_success_log -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer_content_view, LogOfSuccessFragment())
                        .addToBackStack(null).commit()
                    drawer.closeDrawers()
                    true
                }

                R.id.na_test_book -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer_content_view, MaterialFragment())
                        .addToBackStack(null).commit()
                    drawer.closeDrawers()
                    true
                }

                else -> false
            }
        }

    }

    override fun onResume() {
        super.onResume()
        setHamburgerColorBasedOnTheme()
    }

    private fun setHamburgerColorBasedOnTheme() {
        val themeOption = getThemeState()
        when (themeOption) {
            "light" -> {
                toggle?.getDrawerArrowDrawable()
                    ?.setColor(getResources().getColor(R.color.colorText))
            }

            "dark" -> {
                toggle?.getDrawerArrowDrawable()
                    ?.setColor(getResources().getColor(R.color.colorTextNight))
            }
        }
    }

    private fun getThemeState(): String? {
        val sharedPreferences = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        return sharedPreferences.getString("theme_preference", "light")
    }

    private fun currentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.fragmentContainer_content_view)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                    .replace(R.id.fragmentContainer_content_view, SettingsFragment()).commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
