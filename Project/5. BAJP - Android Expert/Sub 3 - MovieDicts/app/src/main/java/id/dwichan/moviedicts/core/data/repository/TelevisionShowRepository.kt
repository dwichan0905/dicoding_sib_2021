package id.dwichan.moviedicts.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource
import id.dwichan.moviedicts.core.util.IdlingResources
import id.dwichan.moviedicts.core.util.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelevisionShowRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    TelevisionShowDataSource {

    private var _trendingToday = MutableLiveData<List<TrendingResultsItem>>()

    private var _trendingWeekly = MutableLiveData<List<TrendingResultsItem>>()

    private var _televisionDetails = MutableLiveData<TelevisionDetailsResponse>()

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
    override fun getTrendingTelevisionShowToday() {
        IdlingResources.increment()

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

                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                _isLoadingToday.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _trendingToday.postValue(ArrayList())
                t.printStackTrace()

                IdlingResources.decrement()
            }
        })
    }

    override fun getTrendingTelevisionShowTodayData(): LiveData<List<TrendingResultsItem>> =
        _trendingToday

    @Suppress("UNCHECKED_CAST")
    override fun getTrendingTelevisionShowWeekly() {
        IdlingResources.increment()

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

                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                _isLoadingWeekly.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _trendingWeekly.postValue(ArrayList())
                t.printStackTrace()

                IdlingResources.decrement()
            }
        })
    }

    override fun getTrendingTelevisionShowWeeklyData(): LiveData<List<TrendingResultsItem>> =
        _trendingWeekly

    override fun getTelevisionShowDetails(id: Int) {
        IdlingResources.increment()
        _isLoadingDetails.value = true
        _errorReason.value = SingleEvent("")

        val api = remoteDataSource.getTvShowDetails(id)
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
                IdlingResources.decrement()
            }

            override fun onFailure(call: Call<TelevisionDetailsResponse>, t: Throwable) {
                _isLoadingDetails.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _televisionDetails.postValue(TelevisionDetailsResponse())
                t.printStackTrace()
                IdlingResources.decrement()
            }
        })
    }

    override fun getTelevisionShowDetailsData(): LiveData<TelevisionDetailsResponse> =
        _televisionDetails
}