package id.dwichan.moviedicts.ui.detail.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.dwichan.moviedicts.core.data.entity.CreatedByDataEntity
import id.dwichan.moviedicts.core.data.entity.GenresDataEntity
import id.dwichan.moviedicts.core.data.entity.ProductionCompaniesDataEntity
import id.dwichan.moviedicts.core.data.entity.TelevisionDetailsDataEntity
import id.dwichan.moviedicts.core.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.core.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
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
class DetailTelevisionShowViewModelTest {

    private val dummyTvId = 94605
    private val dummyTvOriginalName = "Arcane"
    private val dummyFirstAirDate = "2021-11-06"

    private val dummyEmptyTvId = 0

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService
    private lateinit var televisionShowRepository: TelevisionShowRepository
    private lateinit var televisionShowInteractor: TelevisionShowInteractor

    @Mock
    private lateinit var observer: Observer<Resource<TelevisionDetailsDataEntity>>

    @Mock
    private lateinit var observerError: Observer<Resource<TelevisionDetailsDataEntity>>

    @Before
    fun setup() {
        apiService = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
        televisionShowRepository = Mockito.mock(TelevisionShowRepository::class.java)
        televisionShowInteractor = TelevisionShowInteractor(televisionShowRepository)
    }

    @Test
    fun `API Detail TV Show should be successfully accessed and correct values`() {
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
        val liveData = MutableLiveData<Resource<TelevisionDetailsDataEntity>>()
        try {
            val network = NetworkModule.provideApiService(NetworkModule.provideOkHttpClient())
            val call = network.getTelevisionShowDetails(dummyTvId)

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

            // convert creators
            val creatorNet = responseBody.createdBy!!
            val creatorReceived = ArrayList<CreatedByDataEntity>()
            for (position in creatorNet.indices) {
                val item = CreatedByDataEntity(
                    id = creatorNet[position]?.id,
                    name = creatorNet[position]?.name,
                    profilePath = creatorNet[position]?.profilePath,
                    gender = creatorNet[position]?.gender,
                    creditId = creatorNet[position]?.creditId
                )
                creatorReceived.add(item)
            }

            // convert details
            val details = TelevisionDetailsDataEntity(
                voteAverage = responseBody.voteAverage,
                posterPath = responseBody.posterPath,
                backdropPath = responseBody.backdropPath,
                productionCompanies = companyReceived,
                overview = responseBody.overview,
                status = responseBody.status,
                tagline = responseBody.tagline,
                genres = genreReceived,
                name = responseBody.name,
                originalName = responseBody.originalName,
                numberOfSeasons = responseBody.numberOfSeasons,
                numberOfEpisodes = responseBody.numberOfEpisodes,
                firstAirDate = responseBody.firstAirDate,
                episodeRunTime = responseBody.episodeRunTime,
                createdBy = creatorReceived
            )
            liveData.value = Resource.success(details)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TelevisionDetailsResponse>

        Mockito.`when`(televisionShowRepository.getTelevisionShowDetails(dummyTvId)).thenReturn(liveData)
        Mockito.`when`(mockApi.getTelevisionShowDetails(dummyTvId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback =
                it.getArgument(0, Callback::class.java) as Callback<TelevisionDetailsResponse>

            callback.onResponse(mockCall, Response.success(TelevisionDetailsResponse()))

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailTelevisionShowViewModel(televisionShowInteractor)
        viewModel.setTelevisionId(dummyTvId)
        viewModel.tvShowDetails.observeForever(observer)
        Mockito.verify(observer).onChanged(any())
        viewModel.tvShowDetails.removeObserver(observer)
    }

    @Test
    @Suppress("UNCHECKED_CAST")
    fun `ViewModel should be returned error when Television ID is 0`() {
        val liveDataError = MutableLiveData<Resource<TelevisionDetailsDataEntity>>()
        liveDataError.value = Resource.error("stub!", TelevisionDetailsDataEntity())

        val mockApi = Mockito.mock(ApiService::class.java)
        val mockCall = Mockito.mock(Call::class.java) as Call<TelevisionDetailsResponse>
        val mockThrowable = Mockito.mock(Throwable::class.java)

        Mockito.`when`(televisionShowRepository.getTelevisionShowDetails(dummyEmptyTvId)).thenReturn(liveDataError)
        Mockito.`when`(mockApi.getTelevisionShowDetails(dummyEmptyTvId)).thenReturn(mockCall)
        Mockito.doAnswer {
            val callback = it.getArgument(0, Callback::class.java) as Callback<TelevisionDetailsResponse>

            callback.onFailure(mockCall, mockThrowable)

            null
        }.`when`(mockCall).enqueue(any())

        val viewModel = DetailTelevisionShowViewModel(televisionShowInteractor)
        viewModel.setTelevisionId(dummyEmptyTvId)
        viewModel.tvShowDetails.observeForever(observerError)
        Mockito.verify(observerError).onChanged(any())
        viewModel.tvShowDetails.removeObserver(observerError)
    }

    companion object {
        const val TIME_TO_WAIT = 10000L // 10 seconds
    }
}