package id.dwichan.moviedicts.ui.main.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.domain.usecase.MoviesInteractor
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowInteractor
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@RunWith(MockitoJUnitRunner.Silent::class)
class TrendingTelevisionShowViewModelTest {

    private lateinit var viewModel: TrendingTelevisionShowViewModel

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var televisionShowRepository: TelevisionShowRepository
    private lateinit var televisionShowInteractor: TelevisionShowInteractor

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<TrendingResultsItem>>

    @Before
    fun setup() {
        apiService = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
        remoteDataSource = RemoteDataSource(apiService)
        televisionShowRepository = TelevisionShowRepository(remoteDataSource)
        televisionShowInteractor = TelevisionShowInteractor(televisionShowRepository)
        viewModel = TrendingTelevisionShowViewModel(televisionShowInteractor)
    }

    @Test
    fun `API Trending TV Shows Daily should be successfully accessed`() {
        val endpoints = apiService
        val call = endpoints.getTrendingTelevisionShowToday()

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
    fun `API Trending TV Shows Weekly should be successfully accessed`() {
        val endpoints = apiService
        val call = endpoints.getTrendingTelevisionShowWeekly()

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
    fun `ViewModel for Trending Daily should be returned correct values`() {
        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(mockApi.getTrendingMoviesToday()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>
            callback.onResponse(mockCall, Response.success(TrendingResponse()))
            null
        }.`when`(mockApi).getTrendingMoviesToday()

        viewModel.fetchTrendingToday()
        Thread.sleep(SLEEP_WAIT_TIME)
        viewModel.trendingToday.observeForever(observer)
        Mockito.verify(observer).onChanged(anyList())
        viewModel.trendingToday.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel for Trending Weekly should be returned correct values`() {
        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(mockApi.getTrendingMoviesToday()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>
            callback.onResponse(mockCall, Response.success(TrendingResponse()))
            null
        }.`when`(mockApi).getTrendingMoviesToday()

        viewModel.fetchTrendingToday()
        Thread.sleep(SLEEP_WAIT_TIME)
        viewModel.trendingToday.observeForever(observer)
        Mockito.verify(observer).onChanged(anyList())
        viewModel.trendingToday.removeObserver(observer)
    }

    companion object {
        private const val SLEEP_WAIT_TIME = 10000L // 10 seconds
    }
}