package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        val prefDarkMode = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        prefDarkMode?.setOnPreferenceChangeListener { _, newValue ->
            when {
                newValue.equals(resources.getStringArray(R.array.dark_mode_value)[0]) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                newValue.equals(resources.getStringArray(R.array.dark_mode_value)[1]) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                }
                newValue.equals(resources.getStringArray(R.array.dark_mode_value)[2]) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            true
        }
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val prefNotify = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        prefNotify?.setOnPreferenceChangeListener { _, newValue ->
            val dailyReminder = DailyReminder()
            if (newValue.equals(true)) {
                dailyReminder.setDailyReminder(requireContext())
            } else {
                dailyReminder.cancelAlarm(requireContext())
            }
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}