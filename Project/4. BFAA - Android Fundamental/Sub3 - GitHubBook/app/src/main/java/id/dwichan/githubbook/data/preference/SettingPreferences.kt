package id.dwichan.githubbook.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val theme = intPreferencesKey("theme")

    fun getTheme(): Flow<Int> = dataStore.data.map { preference ->
        preference[theme] ?: 0
    }

    suspend fun setTheme(themeId: Int) {
        dataStore.edit { preference ->
            preference[theme] = themeId
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingPreferences =
            INSTANCE ?: synchronized(this) {
                val instance = SettingPreferences(dataStore)
                INSTANCE = instance
                instance
            }
    }
}