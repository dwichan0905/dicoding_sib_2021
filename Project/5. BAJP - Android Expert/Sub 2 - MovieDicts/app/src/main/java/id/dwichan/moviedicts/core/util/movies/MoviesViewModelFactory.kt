package id.dwichan.moviedicts.core.util.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.di.AppScope
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesViewModel
import id.dwichan.moviedicts.ui.main.movies.TrendingMoviesViewModel
import javax.inject.Inject

@AppScope
class MoviesViewModelFactory @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrendingMoviesViewModel::class.java) -> {
                TrendingMoviesViewModel(moviesUseCase) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(moviesUseCase) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }

}