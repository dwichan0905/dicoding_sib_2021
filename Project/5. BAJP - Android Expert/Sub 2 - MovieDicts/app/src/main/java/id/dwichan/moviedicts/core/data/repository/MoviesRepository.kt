package id.dwichan.moviedicts.core.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.core.util.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    MoviesDataSource {

    private var _trendingToday = MutableLiveData<List<TrendingResultsItem>>()

    private var _trendingWeekly = MutableLiveData<List<TrendingResultsItem>>()

    private var _movieDetails = MutableLiveData<MovieDetailsResponse>()

    private var _isLoadingToday = MutableLiveData<Boolean>()

    private var _isLoadingWeekly = MutableLiveData<Boolean>()

    private var _isLoadingDetails = MutableLiveData<Boolean>()

    private var _errorReason = MutableLiveData<SingleEvent<String>>()

    init {
        _errorReason.value = SingleEvent("")
    }

    override fun getLoadingTodayState(): LiveData<Boolean> = _isLoadingToday

    override fun getLoadingWeeklyState(): LiveData<Boolean> = _isLoadingWeekly

    override fun getLoadingDetailsState(): LiveData<Boolean> = _isLoadingDetails

    override fun getErrorReason(): LiveData<SingleEvent<String>> = _errorReason

    @Suppress("UNCHECKED_CAST")
    override fun getTrendingMoviesToday() {
        _isLoadingToday.value = true
        _errorReason.value = SingleEvent("")
        val response = remoteDataSource.getTrendingMoviesToday()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                _isLoadingToday.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        _trendingToday.postValue(responseBody.results as List<TrendingResultsItem>?)
                    } else {
                        _trendingToday.postValue(ArrayList())
                    }
                } else {
                    _errorReason.value = SingleEvent(
                        "Server didn't returned the correct respond [${response.code()}]"
                    )
                    _trendingToday.postValue(ArrayList())
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                _isLoadingToday.value = false
                Log.e("TREND_MOV_ERR", t.message.toString())
                t.printStackTrace()
                _errorReason.value = SingleEvent(t.message as String)
                _trendingToday.postValue(ArrayList())
            }
        })
    }

    override fun getTrendingMoviesTodayData(): LiveData<List<TrendingResultsItem>> = _trendingToday

    @Suppress("UNCHECKED_CAST")
    override fun getTrendingMoviesWeekly() {
        _isLoadingWeekly.value = true
        _errorReason.value = SingleEvent("")
        val response = remoteDataSource.getTrendingMoviesWeekly()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                _isLoadingWeekly.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        _trendingWeekly.postValue(responseBody.results as List<TrendingResultsItem>?)
                    } else {
                        _trendingWeekly.postValue(ArrayList())
                    }
                } else {
                    _errorReason.value = SingleEvent(
                        "Server didn't returned the correct respond [${response.code()}]"
                    )
                    _trendingWeekly.postValue(ArrayList())
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                _isLoadingWeekly.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _trendingWeekly.postValue(ArrayList())
                t.printStackTrace()
            }
        })
    }

    override fun getTrendingMoviesWeeklyData(): LiveData<List<TrendingResultsItem>> =
        _trendingWeekly

    override fun getMovieDetails(id: Int) {
        _isLoadingDetails.value = true
        _errorReason.value = SingleEvent("")

        val api = remoteDataSource.getMovieDetails(id)
        api.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                _isLoadingDetails.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _movieDetails.postValue(responseBody)
                    } else {
                        _errorReason.value = SingleEvent(
                            "Server didn't returned the correct respond [${response.code()}]"
                        )
                        _movieDetails.postValue(MovieDetailsResponse())
                    }
                } else {
                    _errorReason.value = SingleEvent(
                        "Server didn't returned the correct respond [${response.code()}]"
                    )
                    _movieDetails.postValue(MovieDetailsResponse())
                }
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                _isLoadingDetails.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _movieDetails.postValue(MovieDetailsResponse())
                t.printStackTrace()
            }

        })
    }

    override fun getMovieDetailsData(): LiveData<MovieDetailsResponse> = _movieDetails
}