package id.dwichan.githubbook.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import id.dwichan.githubbook.data.preference.SettingPreferences
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModel
import id.dwichan.githubbook.data.preference.SettingPreferencesViewModelFactory
import id.dwichan.githubbook.databinding.ActivitySplashBinding
import id.dwichan.githubbook.ui.main.MainActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = SettingPreferences.getInstance(dataStore)
        val viewModel = ViewModelProvider(this, SettingPreferencesViewModelFactory(preference))[
                SettingPreferencesViewModel::class.java
        ]
        viewModel.getTheme().observe(this) { themeId ->
            when (themeId) {
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }, DELAY_DURATION_IN_MILLIS)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DELAY_DURATION_IN_MILLIS = 3000L
    }
}