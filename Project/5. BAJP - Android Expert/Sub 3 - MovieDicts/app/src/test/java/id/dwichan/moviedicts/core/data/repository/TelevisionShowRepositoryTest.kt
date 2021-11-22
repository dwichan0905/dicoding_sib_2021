package id.dwichan.moviedicts.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelevisionShowRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val televisionShowRepository = FakeTelevisionShowRepository(remoteDataSource)

    private val dummyTvId = 94605

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending TV Shows Today must be returned correct values`() {
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>
        Mockito.`when`(remoteDataSource.getTrendingTvShowToday()).thenReturn(mockCall)
        Mockito.doAnswer {
            val invocation = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            invocation.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(Mockito.any())
        televisionShowRepository.getTrendingTelevisionShowToday()
        Mockito.verify(remoteDataSource).getTrendingTvShowToday()
        val response = televisionShowRepository.getTrendingTelevisionShowTodayData()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending TV Shows Weekly must be returned correct values`() {
        val mockCall = Mockito.mock(Call::class.java) as Call<TrendingResponse>
        Mockito.`when`(remoteDataSource.getTrendingTvShowWeekly()).thenReturn(mockCall)
        Mockito.doAnswer {
            val invocation = it.getArgument(0, Callback::class.java) as Callback<TrendingResponse>

            invocation.onResponse(mockCall, Response.success(TrendingResponse()))

            null
        }.`when`(mockCall).enqueue(Mockito.any())
        televisionShowRepository.getTrendingTelevisionShowWeekly()
        Mockito.verify(remoteDataSource).getTrendingTvShowWeekly()
        val response = televisionShowRepository.getTrendingTelevisionShowWeeklyData()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Movie Details in Repository must be returned correctly`() {
        val mockCall = Mockito.mock(Call::class.java) as Call<TelevisionDetailsResponse>
        Mockito.`when`(remoteDataSource.getTvShowDetails(dummyTvId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val invocation =
                it.getArgument(0, Callback::class.java) as Callback<TelevisionDetailsResponse>

            invocation.onResponse(mockCall, Response.success(TelevisionDetailsResponse()))

            null
        }.`when`(mockCall).enqueue(Mockito.any())
        televisionShowRepository.getTelevisionShowDetails(dummyTvId)
        Mockito.verify(remoteDataSource).getTvShowDetails(dummyTvId)
        val response = televisionShowRepository.getTelevisionShowDetailsData()
        assertNotNull(response)

        val observer = Mockito.mock(Observer::class.java) as Observer<TelevisionDetailsResponse>
        response.observeForever(observer)
        Mockito.verify(observer).onChanged(Mockito.any(TelevisionDetailsResponse::class.java))
        response.removeObserver(observer)
    }
}