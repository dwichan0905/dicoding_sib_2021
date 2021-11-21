package id.dwichan.moviedicts.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Bikin Test buat Repository ini
class MoviesRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val moviesRepository = FakeMoviesRepository(remoteDataSource)

    private val dummyMovieId = 1930

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending Movies Today in Repository must be returned correctly`() {
        val mockCall = mock(Call::class.java) as Call<TrendingResponse>
        `when`(remoteDataSource.getTrendingMoviesToday()).thenReturn(mockCall)
        doAnswer {
            val invocation = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            invocation.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(any())
        moviesRepository.getTrendingMoviesToday()
        verify(remoteDataSource).getTrendingMoviesToday()
        val response = moviesRepository.getTrendingMoviesTodayData()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending Movies Weekly in Repository must be returned correctly`() {
        val mockCall = mock(Call::class.java) as Call<TrendingResponse>
        `when`(remoteDataSource.getTrendingMoviesWeekly()).thenReturn(mockCall)
        doAnswer {
            val invocation = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            invocation.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(any())
        moviesRepository.getTrendingMoviesWeekly()
        verify(remoteDataSource).getTrendingMoviesWeekly()
        val response = moviesRepository.getTrendingMoviesWeeklyData()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Movie Details in Repository must be returned correctly`() {
        val mockCall = mock(Call::class.java) as Call<MovieDetailsResponse>
        `when`(remoteDataSource.getMovieDetails(dummyMovieId)).thenReturn(mockCall)
        doAnswer {
            val invocation =
                it.getArgument(0, Callback::class.java) as Callback<MovieDetailsResponse>

            invocation.onResponse(mockCall, Response.success(MovieDetailsResponse()))

            null
        }.`when`(mockCall).enqueue(any())
        moviesRepository.getMovieDetails(dummyMovieId)
        verify(remoteDataSource).getMovieDetails(dummyMovieId)
        val response = moviesRepository.getMovieDetailsData()
        assertNotNull(response)

        val observer = mock(Observer::class.java) as Observer<MovieDetailsResponse>
        response.observeForever(observer)
        verify(observer).onChanged(any(MovieDetailsResponse::class.java))
        response.removeObserver(observer)
    }
}