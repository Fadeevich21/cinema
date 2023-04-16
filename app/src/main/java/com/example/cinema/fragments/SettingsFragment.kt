package com.example.cinema.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.cinema.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View? = super.onCreateView(inflater, container, savedInstanceState)

        setupRadioGroup(view!!)

        return view
    }

    private fun setupRadioGroup(view: View) {
        val radioGroup: RadioGroup = view.findViewById(R.id.color_theme_group)
        checkRadioButton(radioGroup)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.color_theme_light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.color_theme_dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.color_theme_system -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    private fun checkRadioButton(radioGroup: RadioGroup) {
        when (isNightModeActive()) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> radioGroup.check(R.id.color_theme_system)
            AppCompatDelegate.MODE_NIGHT_YES -> radioGroup.check(R.id.color_theme_dark)
            AppCompatDelegate.MODE_NIGHT_NO -> radioGroup.check(R.id.color_theme_light)
        }
    }

    private fun isNightModeActive(): Int {
        return AppCompatDelegate.getDefaultNightMode()

    }

}