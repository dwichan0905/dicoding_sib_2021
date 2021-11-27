package id.dwichan.moviedicts.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(moviesUseCase: MoviesUseCase) :
    ViewModel() {

    val trendingToday: LiveData<Resource<List<TrendingResultsDataEntity>>> =
        moviesUseCase.getTrendingMoviesToday()

    val trendingWeekly: LiveData<Resource<List<TrendingResultsDataEntity>>> =
        moviesUseCase.getTrendingMoviesWeekly()

}