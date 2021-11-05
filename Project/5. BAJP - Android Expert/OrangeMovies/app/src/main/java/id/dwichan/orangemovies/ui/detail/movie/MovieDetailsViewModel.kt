package id.dwichan.orangemovies.ui.detail.movie

import androidx.lifecycle.ViewModel
import id.dwichan.orangemovies.utility.DataDummy

class MovieDetailsViewModel: ViewModel() {

    private lateinit var movieId: String

    fun setMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun getMovieData() = DataDummy.getMovieData(movieId)

    fun getMovieCrews() = DataDummy.getMovieCrews(movieId)

}