package com.example.cinema.activies

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.cinema.OnMovieSelectedListener
import com.example.cinema.R
import com.example.cinema.enums.FragmentTags
import com.example.cinema.fragments.HomeFragment
import com.example.cinema.fragments.SettingsFragment
import com.example.cinema.fragments.ShopFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), OnMovieSelectedListener {
    private lateinit var bottomNavigationView: BottomNavigationView
    private var activeFragmentTag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settings = getSharedPreferences("UserInfo", 0)
        val theme = settings.getInt("theme", AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
        AppCompatDelegate.setDefaultNightMode(theme)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        var fragmentTag: String = FragmentTags.HOME.tag
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HomeFragment>(R.id.fragment_container, FragmentTags.HOME.tag)
                add<ShopFragment>(R.id.fragment_container, FragmentTags.SHOP.tag)
                add<SettingsFragment>(R.id.fragment_container, FragmentTags.SETTINGS.tag)
            }
            supportFragmentManager.executePendingTransactions()
        } else
            fragmentTag = savedInstanceState.getString("used_fragment_tag")!!

        supportFragmentManager.commit {
            for (fragmentTag in FragmentTags.values())
                detach(supportFragmentManager.findFragmentByTag(fragmentTag.tag)!!)
        }
        loadFragment(fragmentTag)

        bottomNavigationView = findViewById(R.id.navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    loadFragment(FragmentTags.HOME.tag)
                    true
                }

                R.id.action_shop -> {
                    loadFragment(FragmentTags.SHOP.tag)
                    true
                }

                R.id.action_settings -> {
                    loadFragment(FragmentTags.SETTINGS.tag)
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

    override fun onMovieSelected() {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        val settings: SharedPreferences = getSharedPreferences("UserInfo", 0)
        val editor = settings.edit()
        editor.putInt("theme", AppCompatDelegate.getDefaultNightMode())
        editor.apply()
    }
}