package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.util.SingleEvent

interface MoviesUseCase {

    fun getLoadingTodayState(): LiveData<Boolean>

    fun getLoadingWeeklyState(): LiveData<Boolean>

    fun getLoadingDetailsState(): LiveData<Boolean>

    fun getErrorReason(): LiveData<SingleEvent<String>>

    fun getTrendingMoviesToday()

    fun getTrendingMoviesTodayData(): LiveData<List<TrendingResultsItem>>

    fun getTrendingMoviesWeekly()

    fun getTrendingMoviesWeeklyData(): LiveData<List<TrendingResultsItem>>

    fun getMovieDetails(id: Int)

    fun getMovieDetailsData(): LiveData<MovieDetailsResponse>

}