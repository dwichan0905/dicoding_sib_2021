package id.dwichan.mydatastore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val preference: SettingsPreference) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> = preference.getThemeSettings().asLiveData()

    fun saveThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            preference.setThemeSettings(isDarkModeActive)
        }
    }
    
}