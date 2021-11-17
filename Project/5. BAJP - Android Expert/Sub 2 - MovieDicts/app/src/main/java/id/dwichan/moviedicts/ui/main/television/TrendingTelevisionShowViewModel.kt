package id.dwichan.moviedicts.ui.main.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase
import id.dwichan.moviedicts.core.util.SingleEvent
import javax.inject.Inject

class TrendingTelevisionShowViewModel @Inject constructor(
    private val televisionShowUseCase: TelevisionShowUseCase
) : ViewModel() {

    val trendingToday: LiveData<List<TrendingResultsItem>> =
        televisionShowUseCase.getTrendingTelevisionShowTodayData()

    val trendingWeekly: LiveData<List<TrendingResultsItem>> =
        televisionShowUseCase.getTrendingTelevisionShowWeeklyData()

    val isLoadingToday: LiveData<Boolean> =
        televisionShowUseCase.getLoadingTodayState()

    val isLoadingWeekly: LiveData<Boolean> =
        televisionShowUseCase.getLoadingWeeklyState()

    val errorReason: LiveData<SingleEvent<String>> =
        televisionShowUseCase.getErrorReason()


    fun fetchTrendingToday() {
        televisionShowUseCase.getTrendingTelevisionShowToday()
    }

    fun fetchTrendingWeekly() {
        televisionShowUseCase.getTrendingTelevisionShowWeekly()
    }
}