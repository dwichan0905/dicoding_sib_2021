package id.dwichan.moviedicts.ui.main.television

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.entity.TrendingEntity
import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingTelevisionShowViewModel: ViewModel() {

    private var _trendingToday = MutableLiveData<List<TrendingEntity>>()
    val trendingToday: LiveData<List<TrendingEntity>> = _trendingToday

    private var _trendingWeekly = MutableLiveData<List<TrendingEntity>>()
    val trendingWeekly: LiveData<List<TrendingEntity>> = _trendingWeekly

    private var _isLoadingToday = MutableLiveData<Boolean>()
    private var _isLoadingWeekly = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() {
            val value = MutableLiveData<Boolean>()
            value.value = (_isLoadingToday.value == true && _isLoadingWeekly.value == true)
            return value
        }

    fun fetchTrendingToday() {
        val response = ApiService.getApiService().getTrendingTelevisionShowToday()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun fetchTrendingWeekly() {
        val response = ApiService.getApiService().getTrendingTelevisionShowWeekly()
        response.enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}