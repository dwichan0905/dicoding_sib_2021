package id.dwichan.moviedicts.data.repository.remote.api

import id.dwichan.moviedicts.BuildConfig
import id.dwichan.moviedicts.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    companion object {
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
            client.apply {
                if (BuildConfig.DEBUG) {
                    val logging =
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    addInterceptor(logging)
                }
            }
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.TMDB_HOST)
                .client(client.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}