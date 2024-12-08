package com.example.GamblingLogger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val buttonLight: Button = findViewById<Button>(R.id.button_light)
        val buttonDark: Button = findViewById<Button>(R.id.button_dark)


        val PREFS_NAME = "ThemePrefs"
        val PREFS_KEY_THEME = "Theme_Default"

        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val selectedTheme = sharedPreferences.getInt(PREFS_KEY_THEME, R.style.Theme_Default)

        // Apply the selected theme before setting the content view
        setTheme(selectedTheme)

        buttonLight.setOnClickListener { v ->
            val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt(PREFS_KEY_THEME, R.style.Theme_Default)
            editor.apply()
            recreate()
        }
        buttonDark.setOnClickListener { v ->
            val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt(PREFS_KEY_THEME, R.style.Theme_Dark)
            editor.apply()
            recreate()
        }



        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val wagerListFragment: Fragment = WagerListFragment()
        val wagerAddFragment: Fragment = WagerAddFragment()
        val analyticsFragment: Fragment = StatsFragment()


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val intent = Intent(this, WagerDetailActivity::class.java)
        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_home -> fragment = wagerListFragment
                R.id.action_add -> startActivity(intent)
                R.id.action_stats -> fragment = analyticsFragment

            }
            fragmentManager.beginTransaction().replace(R.id.wager_frame_layout, fragment).commit()
            true
        }



        // Set default selection
        bottomNavigationView.selectedItemId = R.id.action_home
    }
}