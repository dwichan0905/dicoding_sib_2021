package id.dwichan.githubbook.data.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class SettingPreferencesViewModelFactory(private val pref: SettingPreferences) :
    NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingPreferencesViewModel::class.java)) {
            return SettingPreferencesViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown class specified: ${modelClass.name}")
    }
}