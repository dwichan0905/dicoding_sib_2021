package id.dwichan.orangemovies.ui.detail.movie

import id.dwichan.orangemovies.utility.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieDetailsViewModelTest {
    private lateinit var viewModel: MovieDetailsViewModel
    private val dummyMovie = DataDummy.generateMovies()[0]
    private val dummyMovieId = dummyMovie.movieId

    @Before
    fun setup() {
        viewModel = MovieDetailsViewModel()
        viewModel.setMovieId(dummyMovieId)
    }

    @Test
    fun getMovies() {
        viewModel.setMovieId(dummyMovie.movieId)
        val actualMovie = viewModel.getMovieData()

        assertNotNull(actualMovie)

        assertEquals(dummyMovie.movieId, actualMovie?.movieId)
        assertEquals(dummyMovie.title, actualMovie?.title)
        assertEquals(dummyMovie.duration, actualMovie?.duration)
        assertEquals(dummyMovie.crews, actualMovie?.crews)
        assertEquals(dummyMovie.poster, actualMovie?.poster)
        assertEquals(dummyMovie.synopsis, actualMovie?.synopsis)
        assertEquals(dummyMovie.releaseDate, actualMovie?.releaseDate)
        assertEquals(dummyMovie.category, actualMovie?.category)
        assertEquals(dummyMovie.certification, actualMovie?.certification)
        assertEquals(dummyMovie.userScore, actualMovie?.userScore)
        assertEquals(dummyMovie.highlight, actualMovie?.highlight)
    }

    @Test
    fun getMovieCrews() {
        viewModel.setMovieId(dummyMovie.movieId)
        val actualMovieCrews = viewModel.getMovieCrews()

        assertNotNull(actualMovieCrews)

        for ((i, crew) in actualMovieCrews.withIndex()) {
            assertEquals(dummyMovie.crews[i].name, crew.name)
            assertEquals(dummyMovie.crews[i].job, crew.job)
        }
    }
}