package id.dwichan.moviedicts.ui.main.television

import androidx.lifecycle.LiveData
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

    val trendingToday: LiveData<Resource<List<TrendingResultsDataEntity>>> =
        televisionShowUseCase.getTrendingTelevisionShowToday()

    val trendingWeekly: LiveData<Resource<List<TrendingResultsDataEntity>>> =
        televisionShowUseCase.getTrendingTelevisionShowWeekly()

}