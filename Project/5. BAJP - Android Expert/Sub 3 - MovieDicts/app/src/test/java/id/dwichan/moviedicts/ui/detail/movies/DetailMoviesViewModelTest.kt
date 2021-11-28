package id.dwichan.moviedicts.ui.detail.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.entity.GenresDataEntity
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.ProductionCompaniesDataEntity
import id.dwichan.moviedicts.core.data.repository.MoviesRepository
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
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
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner.Silent::class)
class DetailMoviesViewModelTest {

    private lateinit var apiService: ApiService
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var moviesInteractor: MoviesInteractor

    private val dummyMovieId = 1930
    private val dummyMovieOriginalTitle = "The Amazing Spider-Man"
    private val dummyReleaseDate = "2012-06-23"

    private val dummyEmptyMovieId = 0

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<MovieDetailsDataEntity>>

    @Mock
    private lateinit var observerError: Observer<Resource<MovieDetailsDataEntity>>

    @Before
    fun setup() {
        apiService = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
        moviesRepository = Mockito.mock(MoviesRepository::class.java)
        moviesInteractor = MoviesInteractor(moviesRepository)
    }

    @Test
    fun `API Detail Movies should be successfully accessed and correct values`() {
        val endpoints = apiService
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
        val liveData = MutableLiveData<Resource<MovieDetailsDataEntity>>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getMovieDetails(dummyMovieId)

            val response = call.execute()
            val responseBody = response.body()

            // convert genres
            val genreNet = responseBody!!.genres!!
            val genreReceived = ArrayList<GenresDataEntity>()
            for (position in genreNet.indices) {
                val item = GenresDataEntity(
                    id = genreNet[position]?.id,
                    name = genreNet[position]?.name
                )
                genreReceived.add(item)
            }

            // convert companies
            val companyNet = responseBody.productionCompanies!!
            val companyReceived = ArrayList<ProductionCompaniesDataEntity>()
            for (position in companyNet.indices) {
                val item = ProductionCompaniesDataEntity(
                    id = companyNet[position]?.id,
                    name = companyNet[position]?.name,
                    logoPath = companyNet[position]?.logoPath
                )
                companyReceived.add(item)
            }

            // convert details
            val details = MovieDetailsDataEntity(
                title = responseBody.title,
                adult = responseBody.adult,
                voteAverage = responseBody.voteAverage,
                posterPath = responseBody.posterPath,
                backdropPath = responseBody.backdropPath,
                originalTitle = responseBody.originalTitle,
                productionCompanies = companyReceived,
                overview = responseBody.overview,
                status = responseBody.status,
                tagline = responseBody.tagline,
                genres = genreReceived,
                runtime = responseBody.runtime,
                releaseDate = responseBody.releaseDate
            )
            liveData.value = Resource.success(details)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<MovieDetailsResponse>

        Mockito.`when`(moviesRepository.getMovieDetails(dummyMovieId)).thenReturn(liveData)
        Mockito.`when`(mockApi.getMovieDetails(dummyMovieId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<MovieDetailsResponse>

            callback.onResponse(mockCall, Response.success(MovieDetailsResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailMoviesViewModel(moviesInteractor)
        viewModel.setMovieId(dummyMovieId)
        viewModel.movieDetails.observeForever(observer)
        Mockito.verify(observer).onChanged(any())
        viewModel.movieDetails.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel should be returned error when Movie ID is 0`() {
        val liveDataError = MutableLiveData<Resource<MovieDetailsDataEntity>>()
        liveDataError.value = Resource.error("stub!", MovieDetailsDataEntity())

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<MovieDetailsResponse>
        val mockThrowable = Mockito.mock(Throwable::class.java)

        Mockito.`when`(moviesRepository.getMovieDetails(dummyEmptyMovieId)).thenReturn(liveDataError)
        Mockito.`when`(mockApi.getMovieDetails(dummyEmptyMovieId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<MovieDetailsResponse>

            callback.onFailure(mockCall, mockThrowable)

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailMoviesViewModel(moviesInteractor)
        viewModel.setMovieId(dummyEmptyMovieId)
        viewModel.movieDetails.observeForever(observerError)
        Mockito.verify(observerError).onChanged(any())
        viewModel.movieDetails.removeObserver(observerError)
    }

    companion object {
        const val TIME_TO_WAIT = 10000L // 10 seconds
    }

}