package id.dwichan.moviedicts.ui.main.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.repository.MoviesRepository
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.domain.usecase.MoviesInteractor
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner.Silent::class)
class TrendingMoviesViewModelTest {

    private lateinit var apiService: ApiService
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var moviesInteractor: MoviesInteractor

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<TrendingResultsItem>>

    @Before
    fun setup() {
        apiService = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
        moviesRepository = Mockito.mock(MoviesRepository::class.java)
        moviesInteractor = MoviesInteractor(moviesRepository)
    }

    @Test
    fun `API Trending Movies Daily should be successfully accessed`() {
        val endpoints = apiService
        val call = endpoints.getTrendingMoviesToday()

        try {
            val response = call.execute()
            val responseBody = response.body()

            assertTrue(response.isSuccessful)
            assertNotNull(responseBody)
            assertNotSame(0, responseBody?.results?.size)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    fun `API Trending Movies Weekly should be successfully accessed`() {
        val endpoints = apiService
        val call = endpoints.getTrendingMoviesWeekly()

        try {
            val response = call.execute()
            val responseBody = response.body()

            assertTrue(response.isSuccessful)
            assertNotNull(responseBody)
            assertNotSame(0, responseBody?.results?.size)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel for Trending Daily should be returned the correct values`() {
        val liveData = MutableLiveData<List<TrendingResultsItem>>()
        try {
            val call = apiService.getTrendingMoviesWeekly()
            val response = call.execute()
            val responseBody = response.body()

            if (responseBody != null) {
                liveData.value = responseBody.results as List<TrendingResultsItem>
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(moviesRepository.getTrendingMoviesTodayData()).thenReturn(liveData)
        Mockito.`when`(mockApi.getTrendingMoviesToday()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            callback.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = TrendingMoviesViewModel(moviesInteractor)
        viewModel.fetchTrendingToday()
        Thread.sleep(SLEEP_WAIT_TIME)
        viewModel.trendingToday.observeForever(observer)
        verify(observer).onChanged(anyList())
        viewModel.trendingToday.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel for Trending Weekly should be returned the correct values`() {
        val liveData = MutableLiveData<List<TrendingResultsItem>>()
        try {
            val call = apiService.getTrendingMoviesWeekly()
            val response = call.execute()
            val responseBody = response.body()

            if (responseBody != null) {
                liveData.value = responseBody.results as List<TrendingResultsItem>
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(moviesRepository.getTrendingMoviesWeeklyData()).thenReturn(liveData)
        Mockito.`when`(mockApi.getTrendingMoviesWeekly()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            callback.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = TrendingMoviesViewModel(moviesInteractor)
        viewModel.fetchTrendingWeekly()
        Thread.sleep(SLEEP_WAIT_TIME)
        viewModel.trendingWeekly.observeForever(observer)
        verify(observer).onChanged(anyList())
        viewModel.trendingWeekly.removeObserver(observer)
    }

    companion object {
        private const val SLEEP_WAIT_TIME = 10000L // 10 seconds
    }
}