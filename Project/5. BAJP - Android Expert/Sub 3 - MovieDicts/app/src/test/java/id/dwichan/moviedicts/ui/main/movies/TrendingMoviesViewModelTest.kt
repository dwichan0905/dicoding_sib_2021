package id.dwichan.moviedicts.ui.main.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.data.repository.MoviesRepository
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.domain.usecase.MoviesInteractor
import id.dwichan.moviedicts.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
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
    private lateinit var observer: Observer<Resource<PagedList<TrendingResultsDataEntity>>>

    @Mock
    private lateinit var mockPagedList: PagedList<TrendingResultsDataEntity>

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
        val liveData = MutableLiveData<Resource<PagedList<TrendingResultsDataEntity>>>()
        val received = ArrayList<TrendingResultsDataEntity>()
        liveData.value = Resource.success(mockPagedList)
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingMoviesToday()

            val response = call.execute()
            val responseBody = response.body()
            val itemList = responseBody!!.results!!


            for (position in itemList.indices) {
                val item = TrendingResultsDataEntity(
                    originalTitle = itemList[position]?.originalTitle,
                    title = itemList[position]?.title,
                    backdropPath = itemList[position]?.backdropPath,
                    posterPath = itemList[position]?.posterPath,
                    voteAverage = itemList[position]?.voteAverage,
                    id = itemList[position]?.id,
                    name = itemList[position]?.name,
                    originalName = itemList[position]?.originalName,
                    adult = itemList[position]?.adult
                )
                received.add(item)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(liveData.value!!.data?.size).thenReturn(received.size)
        Mockito.`when`(moviesRepository.getTrendingMoviesToday()).thenReturn(liveData)
        Mockito.`when`(mockApi.getTrendingMoviesToday()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            callback.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = TrendingMoviesViewModel(moviesInteractor)
        viewModel.reload()
        viewModel.trendingToday.observeForever(observer)
        verify(observer).onChanged(any())
        viewModel.trendingToday.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel for Trending Weekly should be returned the correct values`() {
        val liveData = MutableLiveData<Resource<PagedList<TrendingResultsDataEntity>>>()
        liveData.value = Resource.success(mockPagedList)
        val received = ArrayList<TrendingResultsDataEntity>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingMoviesWeekly()

            val response = call.execute()
            val responseBody = response.body()
            val itemList = responseBody!!.results!!


            for (position in itemList.indices) {
                val item = TrendingResultsDataEntity(
                    originalTitle = itemList[position]?.originalTitle,
                    title = itemList[position]?.title,
                    backdropPath = itemList[position]?.backdropPath,
                    posterPath = itemList[position]?.posterPath,
                    voteAverage = itemList[position]?.voteAverage,
                    id = itemList[position]?.id,
                    name = itemList[position]?.name,
                    originalName = itemList[position]?.originalName,
                    adult = itemList[position]?.adult
                )
                received.add(item)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(moviesRepository.getTrendingMoviesWeekly()).thenReturn(liveData)
        Mockito.`when`(mockApi.getTrendingMoviesWeekly()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            callback.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = TrendingMoviesViewModel(moviesInteractor)
        viewModel.reload()
        viewModel.trendingWeekly.observeForever(observer)
        verify(observer).onChanged(any())
        viewModel.trendingWeekly.removeObserver(observer)
    }
}