package id.dwichan.moviedicts.ui.main.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowInteractor
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
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner.Silent::class)
class TrendingTelevisionShowViewModelTest {

    private lateinit var apiService: ApiService
    private lateinit var televisionShowRepository: TelevisionShowRepository
    private lateinit var televisionShowInteractor: TelevisionShowInteractor

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<List<TrendingResultsDataEntity>>>

    @Before
    fun setup() {
        apiService = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
        televisionShowRepository = Mockito.mock(TelevisionShowRepository::class.java)
        televisionShowInteractor = TelevisionShowInteractor(televisionShowRepository)
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
        val liveData = MutableLiveData<Resource<List<TrendingResultsDataEntity>>>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingTelevisionShowToday()

            val response = call.execute()
            val responseBody = response.body()
            val itemList = responseBody!!.results!!

            val received = ArrayList<TrendingResultsDataEntity>()
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
            liveData.value = Resource.success(received)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(televisionShowRepository.getTrendingTelevisionShowToday())
            .thenReturn(liveData)
        Mockito.`when`(mockApi.getTrendingTelevisionShowToday()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>
            callback.onResponse(mockCall, Response.success(TrendingResponse()))
            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = TrendingTelevisionShowViewModel(televisionShowInteractor)
        viewModel.trendingToday.observeForever(observer)
        Mockito.verify(observer).onChanged(any())
        viewModel.trendingToday.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel for Trending Weekly should be returned correct values`() {
        val liveData = MutableLiveData<Resource<List<TrendingResultsDataEntity>>>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingTelevisionShowToday()

            val response = call.execute()
            val responseBody = response.body()
            val itemList = responseBody!!.results!!

            val received = ArrayList<TrendingResultsDataEntity>()
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
            liveData.value = Resource.success(received)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>

        Mockito.`when`(televisionShowRepository.getTrendingTelevisionShowWeekly())
            .thenReturn(liveData)
        Mockito.`when`(mockApi.getTrendingTelevisionShowWeekly()).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>
            callback.onResponse(mockCall, Response.success(TrendingResponse()))
            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = TrendingTelevisionShowViewModel(televisionShowInteractor)
        viewModel.trendingWeekly.observeForever(observer)
        Mockito.verify(observer).onChanged(any())
        viewModel.trendingWeekly.removeObserver(observer)
    }

    companion object {
        private const val SLEEP_WAIT_TIME = 10000L // 10 seconds
    }
}