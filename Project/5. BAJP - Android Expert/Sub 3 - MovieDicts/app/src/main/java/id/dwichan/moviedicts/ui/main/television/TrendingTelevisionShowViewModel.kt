package id.dwichan.moviedicts.ui.main.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

@HiltViewModel
class TrendingTelevisionShowViewModel @Inject constructor(
    televisionShowUseCase: TelevisionShowUseCase
) : ViewModel() {

    private val reloadTrigger = MutableLiveData<Boolean>()

    val trendingToday: LiveData<Resource<List<TrendingResultsDataEntity>>> = Transformations.switchMap(reloadTrigger) {
        televisionShowUseCase.getTrendingTelevisionShowToday()
    }

    val trendingWeekly: LiveData<Resource<List<TrendingResultsDataEntity>>> = Transformations.switchMap(reloadTrigger) {
        televisionShowUseCase.getTrendingTelevisionShowWeekly()
    }

    fun reload() {
        reloadTrigger.value = true
    }

}