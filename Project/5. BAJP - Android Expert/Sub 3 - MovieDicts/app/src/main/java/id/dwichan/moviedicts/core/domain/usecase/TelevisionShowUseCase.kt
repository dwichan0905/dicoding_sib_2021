package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.util.SingleEvent

interface TelevisionShowUseCase {
    fun getLoadingTodayState(): LiveData<Boolean>

    fun getLoadingWeeklyState(): LiveData<Boolean>

    fun getLoadingDetailsState(): LiveData<Boolean>

    fun getErrorReason(): LiveData<SingleEvent<String>>

    fun getTrendingTelevisionShowToday()

    fun getTrendingTelevisionShowTodayData(): LiveData<List<TrendingResultsItem>>

    fun getTrendingTelevisionShowWeekly()

    fun getTrendingTelevisionShowWeeklyData(): LiveData<List<TrendingResultsItem>>

    fun getTelevisionShowDetails(id: Int)

    fun getTelevisionShowDetailsData(): LiveData<TelevisionDetailsResponse>
}