package id.dwichan.moviedicts.ui.detail.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.MoviesRepository
import id.dwichan.moviedicts.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.util.SingleEvent

class DetailMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    val data: LiveData<MovieDetailsResponse> = moviesRepository.movieDetails

    val isLoading: LiveData<Boolean> = moviesRepository.isLoadingDetails

    val errorReason: LiveData<SingleEvent<String>> = moviesRepository.errorReason

    private var _movieId = MutableLiveData<Int>()

    fun setMovieId(id: Int) {
        _movieId.value = id
    }

    fun fetchMovieDetails() {
        moviesRepository.getMovieDetails(_movieId.value ?: 0)
    }
}