package id.dwichan.mydatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsPreference private constructor(private val dataStore: DataStore<Preferences>){

    private val THEME_KEY = booleanPreferencesKey("theme_settings")

    fun getThemeSettings(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun setThemeSettings(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: SettingsPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingsPreference =
            INSTANCE ?: synchronized(this) {
                val instance = SettingsPreference(dataStore)
                INSTANCE = instance
                instance
            }
    }
}