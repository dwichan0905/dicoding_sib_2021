package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.core.util.SingleEvent
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesDataSource: MoviesDataSource) :
    MoviesUseCase {
    override fun getLoadingTodayState(): LiveData<Boolean> = moviesDataSource.getLoadingTodayState()

    override fun getLoadingWeeklyState(): LiveData<Boolean> =
        moviesDataSource.getLoadingWeeklyState()

    override fun getLoadingDetailsState(): LiveData<Boolean> =
        moviesDataSource.getLoadingDetailsState()

    override fun getErrorReason(): LiveData<SingleEvent<String>> = moviesDataSource.getErrorReason()

    override fun getTrendingMoviesToday() {
        moviesDataSource.getTrendingMoviesToday()
    }

    override fun getTrendingMoviesTodayData(): LiveData<List<TrendingResultsItem>> =
        moviesDataSource.getTrendingMoviesTodayData()

    override fun getTrendingMoviesWeekly() {
        moviesDataSource.getTrendingMoviesWeekly()
    }

    override fun getTrendingMoviesWeeklyData(): LiveData<List<TrendingResultsItem>> =
        moviesDataSource.getTrendingMoviesWeeklyData()

    override fun getMovieDetails(id: Int) {
        moviesDataSource.getMovieDetails(id)
    }

    override fun getMovieDetailsData(): LiveData<MovieDetailsResponse> =
        moviesDataSource.getMovieDetailsData()

}