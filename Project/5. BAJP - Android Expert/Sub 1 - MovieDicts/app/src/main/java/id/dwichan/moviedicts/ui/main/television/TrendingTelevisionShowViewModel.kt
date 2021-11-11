package id.dwichan.moviedicts.ui.main.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.util.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingTelevisionShowViewModel : ViewModel() {

    private var _trendingToday = MutableLiveData<List<TrendingResultsItem>>()
    val trendingToday: LiveData<List<TrendingResultsItem>> = _trendingToday

    private var _trendingWeekly = MutableLiveData<List<TrendingResultsItem>>()
    val trendingWeekly: LiveData<List<TrendingResultsItem>> = _trendingWeekly

    private var _isLoadingToday = MutableLiveData<Boolean>()
    val isLoadingToday: LiveData<Boolean> = _isLoadingToday

    private var _isLoadingWeekly = MutableLiveData<Boolean>()
    val isLoadingWeekly: LiveData<Boolean> = _isLoadingWeekly

    private var _errorReason = MutableLiveData<SingleEvent<String>>()
    val errorReason: LiveData<SingleEvent<String>> = _errorReason

    init {
        _errorReason.value = SingleEvent("")
    }

    @Suppress("UNCHECKED_CAST")
    fun fetchTrendingToday() {
        _isLoadingToday.value = true
        _errorReason.value = SingleEvent("")
        val response = ApiService.getApiService().getTrendingTelevisionShowToday()
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
                _errorReason.value = SingleEvent(t.message as String)
                _trendingToday.postValue(ArrayList())
                t.printStackTrace()
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    fun fetchTrendingWeekly() {
        _isLoadingWeekly.value = true
        _errorReason.value = SingleEvent("")
        val response = ApiService.getApiService().getTrendingTelevisionShowWeekly()
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
}