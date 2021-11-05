package id.dwichan.orangemovies.ui.main.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.dwichan.orangemovies.R
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var highlightMovieAdapter: HighlightMovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val highlightMoviesLayout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val moviesLayoutManager = object: LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rec_highlight_movies.layoutManager = highlightMoviesLayout
        rec_movies.layoutManager = moviesLayoutManager

        highlightMovieAdapter = HighlightMovieAdapter()
        movieAdapter = MovieAdapter()
        rec_movies.adapter = movieAdapter
        rec_highlight_movies.adapter = highlightMovieAdapter

        movieViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
            .get(MovieViewModel::class.java)

        movieAdapter.setMovies(movieViewModel.getMovies())
        highlightMovieAdapter.setMovies(movieViewModel.getHighlightMovies())
    }
}