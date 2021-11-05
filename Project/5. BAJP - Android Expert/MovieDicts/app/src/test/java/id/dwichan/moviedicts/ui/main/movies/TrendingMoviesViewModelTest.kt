package id.dwichan.moviedicts.ui.main.movies

import androidx.test.espresso.IdlingRegistry
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.util.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class TrendingMoviesViewModelTest {

    private lateinit var viewModel: TrendingMoviesViewModel

    @Test fun `Trending Movies Daily should be not returned null or empty`() {
        `when`(viewModel.trendingToday).thenReturn(listOf(TrendingResultsItem))
        viewModel.fetchTrendingToday()
        assertNotEquals(null, viewModel.trendingToday.value?.size)
        assertNotEquals(0, viewModel.trendingToday.value?.size)
    }

    @Test fun `Trending Movies Weekly should be not returned null or empty`() {
        viewModel.fetchTrendingWeekly()
        assertNotEquals(null, viewModel.trendingWeekly.value?.size)
        assertNotEquals(0, viewModel.trendingWeekly.value?.size)
    }

}