package id.dwichan.moviedicts.util.television

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.dwichan.moviedicts.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.di.Injection
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowViewModel
import id.dwichan.moviedicts.ui.main.television.TrendingTelevisionShowViewModel

class TelevisionShowViewModelFactory private constructor(private val mTelevisionShowRepository: TelevisionShowRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrendingTelevisionShowViewModel::class.java) -> {
                TrendingTelevisionShowViewModel(mTelevisionShowRepository) as T
            }
            modelClass.isAssignableFrom(DetailTelevisionShowViewModel::class.java) -> {
                DetailTelevisionShowViewModel(mTelevisionShowRepository) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }

    companion object {

        @Volatile
        private var instance: TelevisionShowViewModelFactory? = null

        fun getInstance(): TelevisionShowViewModelFactory =
            instance ?: synchronized(this) {
                instance
                    ?: TelevisionShowViewModelFactory(Injection.provideTelevisionShowRepository()).apply {
                        instance = this
                    }
            }
    }

}