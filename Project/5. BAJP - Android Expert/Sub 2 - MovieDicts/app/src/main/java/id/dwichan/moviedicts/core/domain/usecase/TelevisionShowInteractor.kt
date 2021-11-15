package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource
import id.dwichan.moviedicts.core.util.SingleEvent
import javax.inject.Inject

class TelevisionShowInteractor @Inject constructor(private val televisionShowDataSource: TelevisionShowDataSource) :
    TelevisionShowUseCase {
    override fun getLoadingTodayState(): LiveData<Boolean> =
        televisionShowDataSource.getLoadingTodayState()

    override fun getLoadingWeeklyState(): LiveData<Boolean> =
        televisionShowDataSource.getLoadingWeeklyState()

    override fun getLoadingDetailsState(): LiveData<Boolean> =
        televisionShowDataSource.getLoadingDetailsState()

    override fun getErrorReason(): LiveData<SingleEvent<String>> =
        televisionShowDataSource.getErrorReason()

    override fun getTrendingTelevisionShowToday() =
        televisionShowDataSource.getTrendingTelevisionShowToday()

    override fun getTrendingTelevisionShowTodayData(): LiveData<List<TrendingResultsItem>> =
        televisionShowDataSource.getTrendingTelevisionShowTodayData()

    override fun getTrendingTelevisionShowWeekly() =
        televisionShowDataSource.getTrendingTelevisionShowWeekly()

    override fun getTrendingTelevisionShowWeeklyData(): LiveData<List<TrendingResultsItem>> =
        televisionShowDataSource.getTrendingTelevisionShowWeeklyData()

    override fun getTelevisionShowDetails(id: Int) =
        televisionShowDataSource.getTelevisionShowDetails(id)

    override fun getTelevisionShowDetailsData(): LiveData<TelevisionDetailsResponse> =
        televisionShowDataSource.getTelevisionShowDetailsData()
}