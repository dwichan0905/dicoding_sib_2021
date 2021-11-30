package id.dwichan.moviedicts.ui.detail.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

@HiltViewModel
class DetailMoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {

    private var _movieId = MutableLiveData<Int>()

    fun setMovieId(id: Int) {
        _movieId.value = id
    }

    val movieDetails: LiveData<Resource<MovieDetailsDataEntity>> =
        Transformations.switchMap(_movieId) { id ->
            moviesUseCase.getMovieDetails(id)
        }

    val bookmarkStatus: LiveData<Boolean> = Transformations.switchMap(_movieId) { id ->
            moviesUseCase.getBookmarkStatus(id)
        }


    fun setAsBookmark(data: MovieTelevisionDataEntity) {
        moviesUseCase.setMovieAsBookmark(data)
    }

    fun removeFromBookmark(data: MovieTelevisionDataEntity) {
        moviesUseCase.removeFromBookmark(data)
    }

}