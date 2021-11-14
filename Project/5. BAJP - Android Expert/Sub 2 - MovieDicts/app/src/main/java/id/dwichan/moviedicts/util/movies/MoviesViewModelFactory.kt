package id.dwichan.moviedicts.util.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.dwichan.moviedicts.data.repository.MoviesRepository
import id.dwichan.moviedicts.di.Injection
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesViewModel
import id.dwichan.moviedicts.ui.main.movies.TrendingMoviesViewModel

class MoviesViewModelFactory private constructor(private val mMoviesRepository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TrendingMoviesViewModel::class.java) -> {
                TrendingMoviesViewModel(mMoviesRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(mMoviesRepository) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }

    companion object {

        @Volatile
        private var instance: MoviesViewModelFactory? = null

        fun getInstance(): MoviesViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MoviesViewModelFactory(Injection.provideMoviesRepository()).apply {
                    instance = this
                }
            }

    }
}