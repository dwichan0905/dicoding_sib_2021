package id.dwichan.moviedicts.core.data.repository.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.util.IdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    @Suppress("UNCHECKED_CAST")
    fun getTrendingMoviesToday(): LiveData<ApiResponse<TrendingResponse>> {
        IdlingResources.increment()

        val trendingMoviesToday = MutableLiveData<ApiResponse<TrendingResponse>>()
        val response = apiService.getTrendingMoviesToday()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        trendingMoviesToday.value = ApiResponse.success(responseBody)
                    } else {
                        trendingMoviesToday.postValue(
                            ApiResponse.empty(
                                "Trending Movies Today is empty.", TrendingResponse()
                            )
                        )
                    }
                } else {
                    trendingMoviesToday.value = ApiResponse.error(
                        "Server didn't returned the correct respond [${response.code()}]",
                        TrendingResponse()
                    )
                }

                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                Log.e("TREND_MOV_ERR", t.message.toString())
                t.printStackTrace()
                trendingMoviesToday.value = ApiResponse.error(
                    t.message as String, TrendingResponse()
                )

                IdlingResources.decrement()
            }
        })
        return trendingMoviesToday
    }

    @Suppress("UNCHECKED_CAST")
    fun getTrendingMoviesWeekly(): LiveData<ApiResponse<TrendingResponse>> {
        IdlingResources.increment()

        val trendingMoviesWeekly = MutableLiveData<ApiResponse<TrendingResponse>>()
        val response = apiService.getTrendingMoviesWeekly()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        trendingMoviesWeekly.value = ApiResponse.success(responseBody)
                    } else {
                        trendingMoviesWeekly.value = ApiResponse.empty(
                            "Trending Movies Weekly is empty.", TrendingResponse()
                        )
                    }
                } else {
                    trendingMoviesWeekly.value = ApiResponse.error(
                        "Server didn't returned the correct respond [${response.code()}]",
                        TrendingResponse()
                    )
                }

                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                trendingMoviesWeekly.value = ApiResponse.error(
                    t.message as String,
                    TrendingResponse()
                )
                t.printStackTrace()

                IdlingResources.decrement()
            }
        })
        return trendingMoviesWeekly
    }

    fun getMovieDetails(id: Int): LiveData<ApiResponse<MovieDetailsResponse>> {
        IdlingResources.increment()

        val movieDetails = MutableLiveData<ApiResponse<MovieDetailsResponse>>()
        val api = apiService.getMovieDetails(id = id)
        api.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        movieDetails.value = ApiResponse.success(responseBody)
                    } else {
                        movieDetails.value = ApiResponse.error(
                            "Server didn't returned the correct respond [${response.code()}]",
                            MovieDetailsResponse()
                        )
                    }
                } else {
                    movieDetails.value = ApiResponse.error(
                        "Server didn't returned the correct respond [${response.code()}]",
                        MovieDetailsResponse()
                    )
                }
                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                movieDetails.value = ApiResponse.error(
                    t.message as String,
                    MovieDetailsResponse()
                )
                t.printStackTrace()
                IdlingResources.decrement()
            }

        })
        return movieDetails
    }

    @Suppress("UNCHECKED_CAST")
    fun getTrendingTvShowToday(): LiveData<ApiResponse<TrendingResponse>> {
        IdlingResources.increment()

        val trendingTvShowToday = MutableLiveData<ApiResponse<TrendingResponse>>()
        val response = apiService.getTrendingTelevisionShowToday()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        trendingTvShowToday.value = ApiResponse.success(responseBody)
                    } else {
                        trendingTvShowToday.value = ApiResponse.empty(
                            "Trending Television Show Today is empty.", TrendingResponse()
                        )
                    }
                } else {
                    trendingTvShowToday.value = ApiResponse.error(
                        "Server didn't returned the correct respond [${response.code()}]",
                        TrendingResponse()
                    )
                }

                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                trendingTvShowToday.value = ApiResponse.error(
                    t.message as String,
                    TrendingResponse()
                )
                t.printStackTrace()

                IdlingResources.decrement()
            }
        })
        return trendingTvShowToday
    }

    @Suppress("UNCHECKED_CAST")
    fun getTrendingTvShowWeekly(): LiveData<ApiResponse<TrendingResponse>> {
        IdlingResources.increment()

        val trendingTvShowWeekly = MutableLiveData<ApiResponse<TrendingResponse>>()
        val response = apiService.getTrendingTelevisionShowWeekly()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        trendingTvShowWeekly.value = ApiResponse.success(responseBody)
                    } else {
                        trendingTvShowWeekly.value = ApiResponse.empty(
                            "Trending Television Show Weekly is empty.", TrendingResponse()
                        )
                    }
                } else {
                    trendingTvShowWeekly.value = ApiResponse.error(
                        "Server didn't returned the correct respond [${response.code()}]",
                        TrendingResponse()
                    )
                }

                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                trendingTvShowWeekly.value = ApiResponse.error(
                    t.message as String,
                    TrendingResponse()
                )
                t.printStackTrace()

                IdlingResources.decrement()
            }
        })
        return trendingTvShowWeekly
    }

    fun getTvShowDetails(id: Int): LiveData<ApiResponse<TelevisionDetailsResponse>> {
        IdlingResources.increment()

        val televisionDetails = MutableLiveData<ApiResponse<TelevisionDetailsResponse>>()
        val api = apiService.getTelevisionShowDetails(id = id)
        api.enqueue(object : Callback<TelevisionDetailsResponse> {
            override fun onResponse(
                call: Call<TelevisionDetailsResponse>,
                response: Response<TelevisionDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        televisionDetails.value = ApiResponse.success(responseBody)
                    } else {
                        televisionDetails.value = ApiResponse.error(
                            "Server didn't returned the correct respond [${response.code()}]",
                            TelevisionDetailsResponse()
                        )
                    }
                } else {
                    televisionDetails.value = ApiResponse.error(
                        "Server didn't returned the correct respond [${response.code()}]",
                        TelevisionDetailsResponse()
                    )
                }
                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TelevisionDetailsResponse>, t: Throwable) {
                televisionDetails.value = ApiResponse.error(
                    t.message as String,
                    TelevisionDetailsResponse()
                )
                t.printStackTrace()
                IdlingResources.decrement()
            }

        })
        return televisionDetails
    }

}