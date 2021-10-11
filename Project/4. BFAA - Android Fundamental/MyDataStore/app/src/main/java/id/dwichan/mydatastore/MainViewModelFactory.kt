package id.dwichan.mydatastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class MainViewModelFactory(private val preference: SettingsPreference) : NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(preference) as T
        }
        throw IllegalArgumentException("Unknown class: ${modelClass.canonicalName}")
    }
}