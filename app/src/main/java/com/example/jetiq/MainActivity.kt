package com.example.jetiq

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.jetiq.databinding.ActivityMainBinding
import com.example.jetiq.fragment.forMainActivity.HomeFragment
import com.example.jetiq.fragment.LogOfSuccessFragment
import com.example.jetiq.fragment.forMainActivity.MaterialFragment
import com.example.jetiq.fragment.forMainActivity.MessageFragment
import com.example.jetiq.fragment.ScheduleMainFragment
import com.example.jetiq.fragment.forMainActivity.SettingsFragment
import com.example.jetiq.fragment.TestBookFragment
import com.example.jetiq.fragment.forMainActivity.WriteMessageFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //TODO add direction (gmaps) for schedule
    lateinit var fab: FloatingActionButton
    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawer = findViewById(R.id.drawer_layout)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )


        drawer.addDrawerListener(toggle)
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toggle.syncState()
        setHamburgerColorBasedOnTheme()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_content_view, HomeFragment()).commit()

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            if (currentFragment() is MessageFragment) {
                changeFragment(WriteMessageFragment())
            } else {
                changeFragment(MessageFragment())
            }
        }

        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    changeFragment(HomeFragment())
                    true
                }

                R.id.nav_schedule -> {
                    changeFragment(ScheduleMainFragment())
                    true
                }

                R.id.nav_material -> {
                    changeFragment(MaterialFragment())
                    true
                }

                R.id.nav_success_log -> {
                    changeFragment(LogOfSuccessFragment())
                    true
                }

                R.id.na_test_book -> {
                    changeFragment(TestBookFragment())
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
        when (getSharedPreferences(
            "app_settings",
            Context.MODE_PRIVATE
        ).getString("theme_preference", "light")) {
            "light" -> {
                toggle.drawerArrowDrawable.color = resources.getColor(R.color.colorText)
            }

            "dark" -> {
                toggle.drawerArrowDrawable.color = resources.getColor(R.color.colorTextNight)
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_content_view, fragment)
            .addToBackStack(null).commit()
        drawer.closeDrawers()
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
                changeFragment(SettingsFragment())
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
