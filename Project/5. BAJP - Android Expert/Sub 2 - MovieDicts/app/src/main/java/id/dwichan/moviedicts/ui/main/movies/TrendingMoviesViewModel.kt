package id.dwichan.moviedicts.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.MoviesRepository
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.util.SingleEvent

class TrendingMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val trendingToday: LiveData<List<TrendingResultsItem>> = moviesRepository.trendingToday

    val trendingWeekly: LiveData<List<TrendingResultsItem>> = moviesRepository.trendingWeekly

    val isLoadingToday: LiveData<Boolean> = moviesRepository.isLoadingToday

    val isLoadingWeekly: LiveData<Boolean> = moviesRepository.isLoadingWeekly

    val errorReason: LiveData<SingleEvent<String>> = moviesRepository.errorReason

    fun fetchTrendingToday() {
        moviesRepository.getTrendingMoviesToday()
    }

    fun fetchTrendingWeekly() {
        moviesRepository.getTrendingMoviesWeekly()
    }
}