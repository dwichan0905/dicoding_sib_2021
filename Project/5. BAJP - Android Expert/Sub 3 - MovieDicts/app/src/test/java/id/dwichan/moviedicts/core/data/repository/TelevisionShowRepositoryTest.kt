package id.dwichan.moviedicts.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.entity.*
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionDetailsEntity
import id.dwichan.moviedicts.core.data.repository.remote.ApiResponse
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.util.AppExecutors
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Type
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class TelevisionShowRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val api = Mockito.mock(ApiService::class.java)
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val appExecutor = Mockito.mock(AppExecutors::class.java)
    private val televisionShowRepository = FakeTelevisionShowRepository(
        remoteDataSource, localDataSource, appExecutor
    )

    private val dummyTvId = 94605

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending TV Shows Today must be returned correct values`() {
        val trendingList = ArrayList<TrendingEntity>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingTelevisionShowToday()

            val response = call.execute()
            val responseBody = response.body()!!

            val list = responseBody.results!!
            for (position in list.indices) {
                val item = TrendingEntity(
                    originalTitle = list[position]?.originalTitle,
                    title = list[position]?.title,
                    posterPath = list[position]?.posterPath,
                    backdropPath = list[position]?.backdropPath,
                    voteAverage = list[position]?.voteAverage,
                    id = list[position]?.id,
                    adult = list[position]?.adult,
                    originalName = list[position]?.originalName,
                    name = list[position]?.name,
                    mediaType = Type.MEDIA_TYPE_TELEVISION,
                    interval = Type.INTERVAL_TODAY,
                    _timestamp = System.currentTimeMillis()
                )
                trendingList.add(item)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Mockito.`when`(localDataSource.getTrendingTelevisionShowToday()).thenReturn(trendingList)
        val response = televisionShowRepository.getTrendingTelevisionShowToday()
        Mockito.verify(localDataSource).getTrendingTelevisionShowToday()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending TV Shows Weekly must be returned correct values`() {
        val trendingList = ArrayList<TrendingEntity>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingTelevisionShowWeekly()

            val response = call.execute()
            val responseBody = response.body()!!

            val list = responseBody.results!!
            for (position in list.indices) {
                val item = TrendingEntity(
                    originalTitle = list[position]?.originalTitle,
                    title = list[position]?.title,
                    posterPath = list[position]?.posterPath,
                    backdropPath = list[position]?.backdropPath,
                    voteAverage = list[position]?.voteAverage,
                    id = list[position]?.id,
                    adult = list[position]?.adult,
                    originalName = list[position]?.originalName,
                    name = list[position]?.name,
                    mediaType = Type.MEDIA_TYPE_TELEVISION,
                    interval = Type.INTERVAL_TODAY,
                    _timestamp = System.currentTimeMillis()
                )
                trendingList.add(item)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Mockito.`when`(localDataSource.getTrendingTelevisionShowWeekly()).thenReturn(trendingList)
        val response = televisionShowRepository.getTrendingTelevisionShowWeekly()
        Mockito.verify(localDataSource).getTrendingTelevisionShowWeekly()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Television Show Details in Repository must be returned correctly`() {
        lateinit var televisionDetailsEntity: TelevisionDetailsEntity
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTelevisionShowDetails(dummyTvId)

            val response = call.execute()
            val responseBody = response.body()!!

            televisionDetailsEntity = TelevisionDetailsEntity(
                tvId = dummyTvId,
                voteAverage = responseBody.voteAverage,
                posterPath = responseBody.posterPath,
                backdropPath = responseBody.backdropPath,
                overview = responseBody.overview,
                status = responseBody.status,
                tagline = responseBody.tagline,
                name = responseBody.name,
                episodeRunTime = responseBody.episodeRunTime?.get(0),
                firstAirDate = responseBody.firstAirDate,
                numberOfEpisodes = responseBody.numberOfEpisodes,
                numberOfSeasons = responseBody.numberOfSeasons,
                originalName = responseBody.originalName
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Mockito.`when`(localDataSource.getTvShowDetails(dummyTvId)).thenReturn(televisionDetailsEntity)
        val response = televisionShowRepository.getTelevisionShowDetails(dummyTvId)
        Mockito.verify(localDataSource).getTvShowDetails(dummyTvId)
        assertNotNull(response)

        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<TelevisionDetailsDataEntity>>
        response.observeForever(observer)
        Mockito.verify(observer).onChanged(Mockito.any())
        response.removeObserver(observer)
    }
}