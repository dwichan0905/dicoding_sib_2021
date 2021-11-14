package id.dwichan.moviedicts.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.moviedicts.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.util.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelevisionShowRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    TelevisionShowDataSource {

    private var _trendingToday = MutableLiveData<List<TrendingResultsItem>>()
    val trendingToday: LiveData<List<TrendingResultsItem>> = _trendingToday

    private var _trendingWeekly = MutableLiveData<List<TrendingResultsItem>>()
    val trendingWeekly: LiveData<List<TrendingResultsItem>> = _trendingWeekly

    private var _televisionDetails = MutableLiveData<TelevisionDetailsResponse>()
    val televisionDetails: LiveData<TelevisionDetailsResponse> = _televisionDetails

    private var _isLoadingToday = MutableLiveData<Boolean>()
    val isLoadingToday: LiveData<Boolean> = _isLoadingToday

    private var _isLoadingWeekly = MutableLiveData<Boolean>()
    val isLoadingWeekly: LiveData<Boolean> = _isLoadingWeekly

    private var _isLoadingDetails = MutableLiveData<Boolean>()
    val isLoadingDetails: LiveData<Boolean> = _isLoadingDetails

    private var _errorReason = MutableLiveData<SingleEvent<String>>()
    val errorReason: LiveData<SingleEvent<String>> = _errorReason

    init {
        _errorReason.value = SingleEvent("")
    }

    @Suppress("UNCHECKED_CAST")
    override fun getTrendingTelevisionShowToday() {
        _isLoadingToday.value = true
        _errorReason.value = SingleEvent("")
        val response = remoteDataSource.getTrendingTvShowToday()
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
    override fun getTrendingTelevisionShowWeekly() {
        _isLoadingWeekly.value = true
        _errorReason.value = SingleEvent("")
        val response = remoteDataSource.getTrendingTvShowWeekly()
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

    override fun getTelevisionShowDetails(id: Int) {
        _isLoadingDetails.value = true
        _errorReason.value = SingleEvent("")

        val api = ApiService.getApiService().getTelevisionShowDetails(id)
        api.enqueue(object : Callback<TelevisionDetailsResponse> {
            override fun onResponse(
                call: Call<TelevisionDetailsResponse>,
                response: Response<TelevisionDetailsResponse>
            ) {
                _isLoadingDetails.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _televisionDetails.postValue(responseBody)
                    } else {
                        _errorReason.value = SingleEvent(
                            "Server didn't returned the correct respond [${response.code()}]"
                        )
                        _televisionDetails.postValue(TelevisionDetailsResponse())
                    }
                } else {
                    _errorReason.value = SingleEvent(
                        "Server didn't returned the correct respond [${response.code()}]"
                    )
                    _televisionDetails.postValue(TelevisionDetailsResponse())
                }
            }

            override fun onFailure(call: Call<TelevisionDetailsResponse>, t: Throwable) {
                _isLoadingDetails.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _televisionDetails.postValue(TelevisionDetailsResponse())
                t.printStackTrace()
            }
        })
    }

    companion object {

        @Volatile
        private var instance: TelevisionShowRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): TelevisionShowRepository =
            instance ?: synchronized(this) {
                instance ?: TelevisionShowRepository(remoteDataSource).apply {
                    instance = this
                }
            }
    }
}