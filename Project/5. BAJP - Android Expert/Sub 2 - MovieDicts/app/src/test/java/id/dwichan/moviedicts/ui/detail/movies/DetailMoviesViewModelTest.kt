package id.dwichan.moviedicts.ui.detail.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.util.SingleEvent
import org.junit.Assert.*
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
class DetailMoviesViewModelTest {

    private val dummyMovieId = 1930
    private val dummyMovieOriginalTitle = "The Amazing Spider-Man"
    private val dummyReleaseDate = "2012-06-23"

    private val dummyEmptyMovieId = 0

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MovieDetailsResponse>

    @Mock
    private lateinit var observerError: Observer<SingleEvent<String>>

    @Test
    fun `API Detail Movies should be successfully accessed and correct values`() {
        val endpoints = ApiService.getApiService()
        val call = endpoints.getMovieDetails(dummyMovieId)

        try {
            val response = call.execute()
            val responseBody = response.body()

            assertTrue(response.isSuccessful)
            assertNotNull(responseBody)
            assertEquals(dummyMovieOriginalTitle, responseBody?.originalTitle)
            assertEquals(dummyReleaseDate, responseBody?.releaseDate)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel should be returned correct values`() {
        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<MovieDetailsResponse>

        Mockito.`when`(mockApi.getMovieDetails(dummyMovieId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<MovieDetailsResponse>

            callback.onResponse(mockCall, Response.success(MovieDetailsResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailMoviesViewModel()
        viewModel.setMovieId(dummyMovieId)
        viewModel.fetchMovieDetails()
        Thread.sleep(TIME_TO_WAIT)
        viewModel.data.observeForever(observer)
        Mockito.verify(observer).onChanged(any(MovieDetailsResponse::class.java))
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel should be returned error when Movie ID is 0`() {
        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<MovieDetailsResponse>
        val mockThrowable = Mockito.mock(Throwable::class.java)

        Mockito.`when`(mockApi.getMovieDetails(dummyEmptyMovieId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<MovieDetailsResponse>

            callback.onResponse(mockCall, Response.success(MovieDetailsResponse()))
            callback.onFailure(mockCall, mockThrowable)

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailMoviesViewModel()
        viewModel.setMovieId(dummyEmptyMovieId)
        viewModel.fetchMovieDetails()
        Thread.sleep(TIME_TO_WAIT)
        viewModel.errorReason.observeForever(observerError)
        Mockito.verify(observerError).onChanged(any())
    }

    companion object {
        const val TIME_TO_WAIT = 10000L // 10 seconds
    }

}