package id.dwichan.moviedicts.ui.main.movies

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.entity.TrendingEntity
import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingMoviesViewModel: ViewModel() {

    private var _trendingToday = MutableLiveData<List<TrendingResultsItem?>>()
    val trendingToday: LiveData<List<TrendingResultsItem?>> = _trendingToday

    private var _trendingWeekly = MutableLiveData<List<TrendingResultsItem?>>()
    val trendingWeekly: LiveData<List<TrendingResultsItem?>> = _trendingWeekly

    private var _isLoadingToday = MutableLiveData<Boolean>()
    private var _isLoadingWeekly = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() {
            val value = MutableLiveData<Boolean>()
            value.value = (_isLoadingToday.value == true && _isLoadingWeekly.value == true)
            return value
        }

    private var _errorReason = MutableLiveData<String?>()
    val errorReason: LiveData<String?> = _errorReason

    init {
        _errorReason.value = null
    }

    fun fetchTrendingToday() {
        _isLoadingToday.value = true
        _errorReason.value += ""
        val response = ApiService.getApiService().getTrendingMoviesToday()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoadingToday.value = false
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        _trendingToday.postValue(responseBody.results)
                    } else {
                        _trendingToday.postValue(ArrayList())
                    }
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                _isLoadingToday.value = false
                _errorReason.value += t.localizedMessage?.toString() + "\n"
                _trendingToday.postValue(ArrayList())
            }
        })
    }

    fun fetchTrendingWeekly() {
        val response = ApiService.getApiService().getTrendingMoviesWeekly()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoadingWeekly.value = false
                    val responseBody = response.body()
                    if (responseBody?.results != null) {
                        _trendingWeekly.postValue(responseBody.results)
                    } else {
                        _trendingWeekly.postValue(ArrayList())
                    }
                }
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                _isLoadingWeekly.value = false
                _errorReason.value += t.localizedMessage?.toString() + "\n"
                _trendingWeekly.postValue(ArrayList())
            }
        })
    }
}