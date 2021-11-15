package id.dwichan.moviedicts.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.core.util.SingleEvent

class TrendingMoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    val trendingToday: LiveData<List<TrendingResultsItem>> =
        moviesUseCase.getTrendingMoviesTodayData()

    val trendingWeekly: LiveData<List<TrendingResultsItem>> =
        moviesUseCase.getTrendingMoviesWeeklyData()

    val isLoadingToday: LiveData<Boolean> = moviesUseCase.getLoadingTodayState()

    val isLoadingWeekly: LiveData<Boolean> = moviesUseCase.getLoadingWeeklyState()

    val errorReason: LiveData<SingleEvent<String>> = moviesUseCase.getErrorReason()

    fun fetchTrendingToday() {
        moviesUseCase.getTrendingMoviesToday()
    }

    fun fetchTrendingWeekly() {
        moviesUseCase.getTrendingMoviesWeekly()
    }
}