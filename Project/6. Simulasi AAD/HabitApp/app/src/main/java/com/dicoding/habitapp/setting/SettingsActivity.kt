package com.dicoding.habitapp.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.habitapp.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            //TODO 11 : Update theme based on value in ListPreference
            val prefKeyDark = findPreference<ListPreference>(getString(R.string.pref_key_dark))
            prefKeyDark?.setOnPreferenceChangeListener { _, newValue ->
                when {
                    newValue.equals(getString(R.string.pref_dark_follow_system)) -> {
                        updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                    newValue.equals(getString(R.string.pref_dark_on)) -> {
                        updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else -> {
                        updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
                true
            }
        }

        private fun updateTheme(mode: Int): Boolean {
            AppCompatDelegate.setDefaultNightMode(mode)
            requireActivity().recreate()
            return true
        }
    }
}