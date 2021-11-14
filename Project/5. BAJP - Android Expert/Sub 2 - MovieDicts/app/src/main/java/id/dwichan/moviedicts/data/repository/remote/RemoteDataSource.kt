package id.dwichan.moviedicts.data.repository.remote

import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import retrofit2.Call

class RemoteDataSource private constructor(val apiService: ApiService) {

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

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service).apply { instance = this }
            }

    }
}