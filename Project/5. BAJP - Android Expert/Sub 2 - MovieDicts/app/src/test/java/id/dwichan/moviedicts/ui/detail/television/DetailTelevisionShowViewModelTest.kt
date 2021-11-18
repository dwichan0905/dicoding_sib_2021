package id.dwichan.moviedicts.ui.detail.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowInteractor
import id.dwichan.moviedicts.core.util.SingleEvent
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
class DetailTelevisionShowViewModelTest {

    private val dummyTvId = 94605
    private val dummyTvOriginalName = "Arcane"
    private val dummyFirstAirDate = "2021-11-06"

    private val dummyEmptyTvId = 0

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var televisionShowRepository: TelevisionShowRepository
    private lateinit var televisionShowInteractor: TelevisionShowInteractor

    @Mock
    private lateinit var observer: Observer<TelevisionDetailsResponse>

    @Mock
    private lateinit var observerError: Observer<SingleEvent<String>>

    @Before
    fun setup() {
        apiService = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
        remoteDataSource = RemoteDataSource(apiService)
        televisionShowRepository = TelevisionShowRepository(remoteDataSource)
        televisionShowInteractor = TelevisionShowInteractor(televisionShowRepository)
    }

    @Test
    fun `API Detail Movies should be successfully accessed and correct values`() {
        val endpoints = apiService
        val call = endpoints.getTelevisionShowDetails(dummyTvId)

        try {
            val response = call.execute()
            val responseBody = response.body()

            assertTrue(response.isSuccessful)
            assertNotNull(responseBody)
            assertEquals(dummyTvOriginalName, responseBody?.originalName)
            assertEquals(dummyFirstAirDate, responseBody?.firstAirDate)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel should be returned correct values`() {
        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TelevisionDetailsResponse>

        Mockito.`when`(mockApi.getTelevisionShowDetails(dummyTvId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback =
                it.getArgument(0, Callback::class.java) as Callback<TelevisionDetailsResponse>

            callback.onResponse(mockCall, Response.success(TelevisionDetailsResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailTelevisionShowViewModel(televisionShowInteractor)
        viewModel.setTelevisionId(dummyTvId)
        viewModel.fetchTelevisionShowDetails()
        Thread.sleep(TIME_TO_WAIT)
        viewModel.data.observeForever(observer)
        Mockito.verify(observer).onChanged(any(TelevisionDetailsResponse::class.java))
        viewModel.data.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel should be returned error when Television ID is 0`() {
        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TelevisionDetailsResponse>
        val mockThrowable = Mockito.mock(Throwable::class.java)

        Mockito.`when`(mockApi.getTelevisionShowDetails(dummyEmptyTvId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback =
                it.getArgument(0, Callback::class.java) as Callback<TelevisionDetailsResponse>

            callback.onResponse(mockCall, Response.success(TelevisionDetailsResponse()))
            callback.onFailure(mockCall, mockThrowable)

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailTelevisionShowViewModel(televisionShowInteractor)
        viewModel.setTelevisionId(dummyEmptyTvId)
        viewModel.fetchTelevisionShowDetails()
        Thread.sleep(TIME_TO_WAIT)
        viewModel.errorReason.observeForever(observerError)
        Mockito.verify(observerError).onChanged(any())
        viewModel.errorReason.removeObserver(observerError)
    }

    companion object {
        const val TIME_TO_WAIT = 10000L // 10 seconds
    }
}