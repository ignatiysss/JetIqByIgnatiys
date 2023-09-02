package com.example.jetiq

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import com.example.jetiq.databinding.ActivityMainBinding
import com.example.jetiq.fragment.HomeFragment
import com.example.jetiq.fragment.MessageFragment
import com.example.jetiq.fragment.ScheduleMainFragment
import com.example.jetiq.fragment.SettingsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var fab: FloatingActionButton
    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer_content_view, HomeFragment()).commit()

        fab =
            findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
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
                    Toast.makeText(this, "Додому", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_schedule -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer_content_view, ScheduleMainFragment())
                        .addToBackStack(null).commit()
                    true
                }

                R.id.nav_material -> {
                    Toast.makeText(this, "Матеріали", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_success_log -> {
                    Toast.makeText(this, "Журнал успіхів", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.na_test_book -> {
                    Toast.makeText(this, "Тестова книга", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

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
                    .replace(R.id.fragmentContainer_content_view, SettingsFragment())
                    .commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
