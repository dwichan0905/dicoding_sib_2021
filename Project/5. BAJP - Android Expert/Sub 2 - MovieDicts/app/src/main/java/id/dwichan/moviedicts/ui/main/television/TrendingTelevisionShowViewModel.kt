package id.dwichan.moviedicts.ui.main.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.util.SingleEvent

class TrendingTelevisionShowViewModel(private val televisionShowRepository: TelevisionShowRepository) :
    ViewModel() {

    val trendingToday: LiveData<List<TrendingResultsItem>> = televisionShowRepository.trendingToday

    val trendingWeekly: LiveData<List<TrendingResultsItem>> =
        televisionShowRepository.trendingWeekly

    val isLoadingToday: LiveData<Boolean> = televisionShowRepository.isLoadingToday

    val isLoadingWeekly: LiveData<Boolean> = televisionShowRepository.isLoadingWeekly

    val errorReason: LiveData<SingleEvent<String>> = televisionShowRepository.errorReason


    fun fetchTrendingToday() {
        televisionShowRepository.getTrendingTelevisionShowToday()
    }

    fun fetchTrendingWeekly() {
        televisionShowRepository.getTrendingTelevisionShowWeekly()
    }
}