package id.dwichan.orangemovies.ui.main.movies

import id.dwichan.orangemovies.utility.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val expectedMovies = viewModel.getMovies()
        val actualMovies = DataDummy.generateMovies()

        assertNotNull(expectedMovies)
        assertNotNull(actualMovies)

        assertEquals(expectedMovies.size, actualMovies.size)
    }

    @Test
    fun getHighlightMovies() {
        val expectedHighlightMovies = viewModel.getHighlightMovies()
        val actualHighlightMovies = DataDummy.getHighlightMovies()

        assertNotNull(expectedHighlightMovies)
        assertNotNull(actualHighlightMovies)

        assertEquals(expectedHighlightMovies, actualHighlightMovies)
    }
}