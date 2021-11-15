package id.dwichan.moviedicts.core.data.repository.remote

import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getTrendingMoviesToday(): Call<TrendingResponse> =
        apiService.getTrendingMoviesToday()

    fun getTrendingMoviesWeekly(): Call<TrendingResponse> =
        apiService.getTrendingMoviesWeekly()

    fun getMovieDetails(id: Int): Call<MovieDetailsResponse> =
        apiService.getMovieDetails(id = id)

    fun getTrendingTvShowToday(): Call<TrendingResponse> =
        apiService.getTrendingTelevisionShowToday()

    fun getTrendingTvShowWeekly(): Call<TrendingResponse> =
        apiService.getTrendingTelevisionShowWeekly()

    fun getTvShowDetails(id: Int): Call<TelevisionDetailsResponse> =
        apiService.getTelevisionShowDetails(id = id)

}