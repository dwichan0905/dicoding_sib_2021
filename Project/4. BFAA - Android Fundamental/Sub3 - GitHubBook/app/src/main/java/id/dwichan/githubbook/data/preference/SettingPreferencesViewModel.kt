package id.dwichan.githubbook.data.preference

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingPreferencesViewModel(private val pref: SettingPreferences) : ViewModel() {

    fun getTheme(): LiveData<Int> = pref.getTheme().asLiveData()

    fun setTheme(themeId: Int) {
        viewModelScope.launch {
            pref.setTheme(themeId)
        }
    }

}