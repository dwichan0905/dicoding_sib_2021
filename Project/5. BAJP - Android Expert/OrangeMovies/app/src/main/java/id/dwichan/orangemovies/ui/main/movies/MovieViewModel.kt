package id.dwichan.orangemovies.ui.main.movies

import androidx.lifecycle.ViewModel
import id.dwichan.orangemovies.data.Movie
import id.dwichan.orangemovies.utility.DataDummy

class MovieViewModel: ViewModel() {
    fun getMovies(): ArrayList<Movie> = DataDummy.generateMovies()

    fun getHighlightMovies(): ArrayList<Movie> = DataDummy.getHighlightMovies()
}