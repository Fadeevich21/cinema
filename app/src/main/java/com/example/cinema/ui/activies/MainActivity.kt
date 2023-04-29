package com.example.cinema.ui.activies

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.cinema.R
import com.example.cinema.utils.FragmentsTag
import com.example.cinema.ui.fragments.HomeFragment
import com.example.cinema.ui.fragments.MoviePostersFragment
import com.example.cinema.ui.fragments.SettingsFragment
import com.example.cinema.ui.fragments.ShopFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private var activeFragmentTag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setupTheme()

        var fragmentTag: String = FragmentsTag.HOME.tag
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragment_container, FragmentsTag.HOME.tag)
                add<ShopFragment>(R.id.fragment_container, FragmentsTag.SHOP.tag)
                add<SettingsFragment>(R.id.fragment_container, FragmentsTag.SETTINGS.tag)
                add<MoviePostersFragment>(R.id.fragment_container, FragmentsTag.MOVIE_POSTERS.tag)
            }
            supportFragmentManager.executePendingTransactions()
        } else
            fragmentTag = savedInstanceState.getString("used_fragment_tag")!!

        supportFragmentManager.commit {
            for (fragmentTag in FragmentsTag.values())
                detach(supportFragmentManager.findFragmentByTag(fragmentTag.tag)!!)
        }
        loadFragment(fragmentTag)

        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    loadFragment(FragmentsTag.HOME.tag)
                    true
                }

                R.id.action_movie_posters -> {
                    loadFragment(FragmentsTag.MOVIE_POSTERS.tag)
                    true
                }

                R.id.action_shop -> {
                    loadFragment(FragmentsTag.SHOP.tag)
                    true
                }

                R.id.action_settings -> {
                    loadFragment(FragmentsTag.SETTINGS.tag)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("used_fragment_tag", activeFragmentTag)
    }

    override fun onPause() {
        super.onPause()
        val settings: SharedPreferences = getSharedPreferences("UserInfo", 0)
        val editor = settings.edit()
        editor.putInt("theme", AppCompatDelegate.getDefaultNightMode())
        editor.apply()
    }


    private fun setupTheme() {
        val settings = getSharedPreferences("UserInfo", 0)
        val theme = settings.getInt("theme", AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
        AppCompatDelegate.setDefaultNightMode(theme)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

    private fun loadFragment(fragmentTag: String) {
        if (activeFragmentTag == fragmentTag)
            return

        val transaction = supportFragmentManager.beginTransaction()
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(activeFragmentTag)
        if (fragment != null)
            transaction.detach(fragment)

        activeFragmentTag = fragmentTag
        fragment = supportFragmentManager.findFragmentByTag(activeFragmentTag)
        transaction.attach(fragment!!)
        transaction.commit()
    }
}