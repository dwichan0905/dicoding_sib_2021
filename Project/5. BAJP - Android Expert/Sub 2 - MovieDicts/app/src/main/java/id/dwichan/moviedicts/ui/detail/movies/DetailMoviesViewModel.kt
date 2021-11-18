package id.dwichan.moviedicts.ui.detail.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.core.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
class DetailMoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {

    val data: LiveData<MovieDetailsResponse> = moviesUseCase.getMovieDetailsData()

    val isLoading: LiveData<Boolean> = moviesUseCase.getLoadingDetailsState()

    val errorReason: LiveData<SingleEvent<String>> = moviesUseCase.getErrorReason()

    private var _movieId = MutableLiveData<Int>()

    fun setMovieId(id: Int) {
        _movieId.value = id
    }

    fun fetchMovieDetails() {
        moviesUseCase.getMovieDetails(_movieId.value ?: 0)
    }
}