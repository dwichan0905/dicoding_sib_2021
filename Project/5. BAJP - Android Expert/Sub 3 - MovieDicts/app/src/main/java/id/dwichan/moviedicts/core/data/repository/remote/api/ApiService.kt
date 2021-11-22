package id.dwichan.moviedicts.core.data.repository.remote.api

import id.dwichan.moviedicts.BuildConfig
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    fun getTrendingMoviesToday(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_TOKEN
    ): Call<TrendingResponse>

    @GET("trending/movie/week")
    fun getTrendingMoviesWeekly(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_TOKEN
    ): Call<TrendingResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_TOKEN
    ): Call<MovieDetailsResponse>

    @GET("trending/tv/day")
    fun getTrendingTelevisionShowToday(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_TOKEN
    ): Call<TrendingResponse>

    @GET("trending/tv/week")
    fun getTrendingTelevisionShowWeekly(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_TOKEN
    ): Call<TrendingResponse>

    @GET("tv/{tv_id}")
    fun getTelevisionShowDetails(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_TOKEN
    ): Call<TelevisionDetailsResponse>

}