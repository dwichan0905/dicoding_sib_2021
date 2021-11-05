package id.dwichan.orangemovies.ui.detail.tvshow

import id.dwichan.orangemovies.utility.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TvShowDetailsViewModelTest {
    private lateinit var viewModel: TvShowDetailsViewModel
    private val dummyTvShow = DataDummy.generateTvShows()[0]
    private val dummyTvShowId = dummyTvShow.tvShowId

    @Before
    fun setup() {
        viewModel = TvShowDetailsViewModel()
        viewModel.setTvShowId(dummyTvShowId)
    }

    @Test
    fun getTvShows() {
        viewModel.setTvShowId(dummyTvShow.tvShowId)
        val actualTvShow = viewModel.getTvShowData()

        assertNotNull(actualTvShow)

        assertEquals(dummyTvShow.tvShowId, actualTvShow.tvShowId)
        assertEquals(dummyTvShow.title, actualTvShow.title)
        assertEquals(dummyTvShow.duration, actualTvShow.duration)
        assertEquals(dummyTvShow.crews, actualTvShow.crews)
        assertEquals(dummyTvShow.poster, actualTvShow.poster)
        assertEquals(dummyTvShow.synopsis, actualTvShow.synopsis)
        assertEquals(dummyTvShow.year, actualTvShow.year)
        assertEquals(dummyTvShow.category, actualTvShow.category)
        assertEquals(dummyTvShow.certification, actualTvShow.certification)
        assertEquals(dummyTvShow.userScore, actualTvShow.userScore)
    }

    @Test
    fun getTvShowCrews() {
        viewModel.setTvShowId(dummyTvShow.tvShowId)
        val actualTvShowCrews = viewModel.getTvShowCrews()

        assertNotNull(actualTvShowCrews)

        for ((i, crew) in actualTvShowCrews.withIndex()) {
            assertEquals(dummyTvShow.crews[i].name, crew.name)
            assertEquals(dummyTvShow.crews[i].job, crew.job)
        }
    }
}