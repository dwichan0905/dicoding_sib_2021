package id.dwichan.moviedicts.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieDetailsEntity
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.di.NetworkModule
import id.dwichan.moviedicts.core.util.AppExecutors
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Type
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import java.io.IOException

class MoviesRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val api = mock(ApiService::class.java)
    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutor = mock(AppExecutors::class.java)

    private val moviesRepository = FakeMoviesRepository(
        remoteDataSource, localDataSource, appExecutor
    )

    private val dummyMovieId = 1930

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending Movies Today in Repository must be returned correctly`() {
        val trendingList = ArrayList<TrendingEntity>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingMoviesToday()

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
                    mediaType = Type.MEDIA_TYPE_MOVIES,
                    interval = Type.INTERVAL_TODAY,
                    _timestamp = System.currentTimeMillis()
                )
                trendingList.add(item)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        `when`(localDataSource.getTrendingMoviesToday()).thenReturn(trendingList)
        val movieResponse = moviesRepository.getTrendingMoviesToday()
        verify(localDataSource).getTrendingMoviesToday()
        assertNotNull(movieResponse)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Trending Movies Weekly in Repository must be returned correctly`() {
        val trendingList = ArrayList<TrendingEntity>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTrendingMoviesWeekly()

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
                    mediaType = Type.MEDIA_TYPE_MOVIES,
                    interval = Type.INTERVAL_WEEKLY,
                    _timestamp = System.currentTimeMillis()
                )
                trendingList.add(item)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        `when`(localDataSource.getTrendingMoviesWeekly()).thenReturn(trendingList)
        val response = moviesRepository.getTrendingMoviesWeekly()
        verify(localDataSource).getTrendingMoviesWeekly()
        assertNotNull(response)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun `Get Movie Details in Repository must be returned correctly`() {
        lateinit var movieDetailsEntity: MovieDetailsEntity
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getMovieDetails(dummyMovieId)

            val response = call.execute()
            val responseBody = response.body()!!

            movieDetailsEntity = MovieDetailsEntity(
                id = dummyMovieId,
                releaseDate = responseBody.releaseDate,
                runtime = responseBody.runtime,
                tagline = responseBody.tagline,
                status = responseBody.status,
                overview = responseBody.overview,
                originalTitle = responseBody.originalTitle,
                backdropPath = responseBody.backdropPath,
                posterPath = responseBody.posterPath,
                voteAverage = responseBody.voteAverage,
                adult = responseBody.adult,
                title = responseBody.title
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        `when`(localDataSource.getMovieDetails(dummyMovieId)).thenReturn(movieDetailsEntity)
        val response = moviesRepository.getMovieDetails(dummyMovieId)
        verify(localDataSource).getMovieDetails(dummyMovieId)
        assertNotNull(response)

        val observer = mock(Observer::class.java) as Observer<Resource<MovieDetailsDataEntity>>
        response.observeForever(observer)
        verify(observer).onChanged(any())
        response.removeObserver(observer)
    }
}