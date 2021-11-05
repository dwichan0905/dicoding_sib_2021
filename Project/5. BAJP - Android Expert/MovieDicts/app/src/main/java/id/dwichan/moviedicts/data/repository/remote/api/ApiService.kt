package id.dwichan.moviedicts.data.repository.remote.api

import id.dwichan.moviedicts.BuildConfig
import id.dwichan.moviedicts.data.repository.remote.response.MovieDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.MovieSearchResponse
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionSearchResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/movies")
    @Headers(AUTH_HEADER)
    fun searchMovies(
        @Query("query") query: String
    ): Call<MovieSearchResponse>

    @GET("/trending/movies/day")
    @Headers(AUTH_HEADER)
    fun getTrendingMoviesToday(): Call<TrendingResponse>

    @GET("/trending/movies/week")
    @Headers(AUTH_HEADER)
    fun getTrendingMoviesWeekly(): Call<TrendingResponse>

    @GET("/movie/{movie_id}")
    @Headers(AUTH_HEADER)
    fun getMovieDetails(
        @Path("movie_id") id: Int
    ): Call<MovieDetailsResponse>

    @GET("/trending/tv/day")
    @Headers(AUTH_HEADER)
    fun getTrendingTelevisionShowToday(): Call<TrendingResponse>

    @GET("/trending/tv/week")
    @Headers(AUTH_HEADER)
    fun getTrendingTelevisionShowWeekly(): Call<TrendingResponse>

    @GET("/search/tv")
    @Headers(AUTH_HEADER)
    fun searchTelevisionShow(
        @Query("query") query: String
    ): Call<TelevisionSearchResponse>

    @GET("/tv/{tv_id}")
    @Headers(AUTH_HEADER)
    fun getTelevisionShowDetails(
        @Path("tv_id") id: Int
    ): Call<TelevisionDetailsResponse>

    companion object {
        private const val AUTH_HEADER = "Authorization: Bearer ${BuildConfig.TMDB_API_TOKEN}"

        fun getApiService(): ApiService {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(logging)
                }
            }
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}