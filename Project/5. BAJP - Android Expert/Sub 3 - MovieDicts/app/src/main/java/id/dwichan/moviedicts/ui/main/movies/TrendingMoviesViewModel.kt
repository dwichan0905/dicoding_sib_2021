package id.dwichan.moviedicts.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(moviesUseCase: MoviesUseCase) :
    ViewModel() {

    private val reloadTrigger = MutableLiveData<Boolean>()

    val trendingToday: LiveData<Resource<PagedList<TrendingResultsDataEntity>>> = Transformations.switchMap(reloadTrigger) {
        moviesUseCase.getTrendingMoviesToday()
    }

    val trendingWeekly: LiveData<Resource<PagedList<TrendingResultsDataEntity>>> = Transformations.switchMap(reloadTrigger) {
        moviesUseCase.getTrendingMoviesWeekly()
    }

    fun reload() {
        reloadTrigger.value = true
    }

}