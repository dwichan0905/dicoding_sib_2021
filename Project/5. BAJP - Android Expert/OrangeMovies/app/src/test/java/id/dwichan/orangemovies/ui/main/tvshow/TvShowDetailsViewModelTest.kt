package id.dwichan.orangemovies.ui.main.tvshow

import id.dwichan.orangemovies.utility.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowDetailsViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val expectedTvShow = viewModel.getTvShows()
        val actualTvShows = DataDummy.generateTvShows()

        assertNotNull(expectedTvShow)
        assertNotNull(actualTvShows)

        assertEquals(expectedTvShow, actualTvShows)
    }
}