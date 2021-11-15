package id.dwichan.moviedicts.core.util.television

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase
import id.dwichan.moviedicts.di.AppScope
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowViewModel
import id.dwichan.moviedicts.ui.main.television.TrendingTelevisionShowViewModel
import javax.inject.Inject

@AppScope
class TelevisionShowViewModelFactory @Inject constructor(private val televisionShowUseCase: TelevisionShowUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrendingTelevisionShowViewModel::class.java) -> {
                TrendingTelevisionShowViewModel(televisionShowUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTelevisionShowViewModel::class.java) -> {
                DetailTelevisionShowViewModel(televisionShowUseCase) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }

}