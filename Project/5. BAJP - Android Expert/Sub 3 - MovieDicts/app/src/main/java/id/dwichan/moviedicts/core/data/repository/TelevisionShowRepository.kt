package id.dwichan.moviedicts.core.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import id.dwichan.moviedicts.core.data.entity.*
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.data.repository.local.entity.FavoriteEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionCreatedByEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionCreatorCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.remote.ApiResponse
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource
import id.dwichan.moviedicts.core.util.AppExecutors
import id.dwichan.moviedicts.core.util.NetUtil
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelevisionShowRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    @ApplicationContext private val context: Context
) : TelevisionShowDataSource {

    override fun getFavoriteStatus(id: Int): Boolean {
        val db = localDataSource.getFavoriteMovie(id)
        return db.isNotEmpty()
    }

    override fun setTvShowAsFavorite(data: MovieTelevisionDataEntity) {
        appExecutors.diskIO().execute {
            val item = FavoriteEntity(
                id = data.id,
                title = data.title,
                posterPath = data.posterPath,
                backdropPath = data.backdropPath,
                mediaType = Type.MEDIA_TYPE_MOVIES
            )
            localDataSource.insertFavoriteMovie(item)
        }
    }

    override fun removeFavoriteTvShow(data: MovieTelevisionDataEntity) {
        appExecutors.diskIO().execute {
            val item = FavoriteEntity(
                id = data.id,
                title = data.title,
                posterPath = data.posterPath,
                backdropPath = data.backdropPath,
                mediaType = Type.MEDIA_TYPE_MOVIES
            )
            localDataSource.deleteFavorite(item)
        }
    }

    override fun getTrendingTelevisionShowToday(): LiveData<Resource<List<TrendingResultsDataEntity>>> {
        return object :
            NetworkBoundResource<List<TrendingResultsDataEntity>, TrendingResponse>(appExecutors) {
            override fun loadFromDatabase(): LiveData<List<TrendingResultsDataEntity>> {
                val liveData = MutableLiveData<List<TrendingResultsDataEntity>>()
                val data = ArrayList<TrendingResultsDataEntity>()
                val db = localDataSource.getTrendingTelevisionShowToday()

                for (position in db.indices) {
                    val entity = TrendingResultsDataEntity(
                        id = db[position].id,
                        originalName = db[position].originalName,
                        name = db[position].name,
                        originalTitle = db[position].originalTitle,
                        title = db[position].title,
                        posterPath = db[position].posterPath,
                        backdropPath = db[position].backdropPath,
                        adult = db[position].adult,
                        voteAverage = db[position].voteAverage
                    )
                    data.add(entity)
                }
                liveData.value = data

                return liveData
            }

            override fun shouldFetch(data: List<TrendingResultsDataEntity>?): Boolean {
                return if (NetUtil.isOnline(context)) {
                    true
                } else {
                    data == null || data.isEmpty()
                }
            }

            override fun createCall(): LiveData<ApiResponse<TrendingResponse>> {
                return remoteDataSource.getTrendingTvShowToday()
            }

            override fun saveCallResult(data: TrendingResponse) {
                if (data.results != null) {
                    val item = data.results
                    for (position in item.indices) {
                        val entity = TrendingEntity(
                            id = item[position]?.id,
                            title = item[position]?.title,
                            originalTitle = item[position]?.originalTitle,
                            name = item[position]?.name,
                            originalName = item[position]?.originalName,
                            voteAverage = item[position]?.voteAverage,
                            posterPath = item[position]?.posterPath,
                            backdropPath = item[position]?.backdropPath,
                            adult = item[position]?.adult,
                            mediaType = Type.MEDIA_TYPE_TELEVISION,
                            interval = Type.INTERVAL_TODAY
                        )
                        localDataSource.insertTrendingEntity(entity)
                    }
                }
            }

        }.asLiveData()
    }

    override fun getTrendingTelevisionShowWeekly(): LiveData<Resource<List<TrendingResultsDataEntity>>> {
        return object :
            NetworkBoundResource<List<TrendingResultsDataEntity>, TrendingResponse>(appExecutors) {
            override fun loadFromDatabase(): LiveData<List<TrendingResultsDataEntity>> {
                val liveData = MutableLiveData<List<TrendingResultsDataEntity>>()
                val data = ArrayList<TrendingResultsDataEntity>()
                val db = localDataSource.getTrendingTelevisionShowWeekly()

                for (position in db.indices) {
                    val entity = TrendingResultsDataEntity(
                        id = db[position].id,
                        originalName = db[position].originalName,
                        name = db[position].name,
                        originalTitle = db[position].originalTitle,
                        title = db[position].title,
                        posterPath = db[position].posterPath,
                        backdropPath = db[position].backdropPath,
                        adult = db[position].adult,
                        voteAverage = db[position].voteAverage
                    )
                    data.add(entity)
                }
                liveData.value = data

                return liveData
            }

            override fun shouldFetch(data: List<TrendingResultsDataEntity>?): Boolean {
                return if (NetUtil.isOnline(context)) {
                    true
                } else {
                    data == null || data.isEmpty()
                }
            }

            override fun createCall(): LiveData<ApiResponse<TrendingResponse>> {
                return remoteDataSource.getTrendingTvShowWeekly()
            }

            override fun saveCallResult(data: TrendingResponse) {
                if (data.results != null) {
                    val item = data.results
                    for (position in item.indices) {
                        val entity = TrendingEntity(
                            id = item[position]?.id,
                            title = item[position]?.title,
                            originalTitle = item[position]?.originalTitle,
                            name = item[position]?.name,
                            originalName = item[position]?.originalName,
                            voteAverage = item[position]?.voteAverage,
                            posterPath = item[position]?.posterPath,
                            backdropPath = item[position]?.backdropPath,
                            adult = item[position]?.adult,
                            mediaType = Type.MEDIA_TYPE_TELEVISION,
                            interval = Type.INTERVAL_WEEKLY
                        )
                        localDataSource.insertTrendingEntity(entity)
                    }
                }
            }

        }.asLiveData()
    }

    @Suppress("SENSELESS_COMPARISON") // avoid warning comparison always true
    override fun getTelevisionShowDetails(id: Int): LiveData<Resource<TelevisionDetailsDataEntity>> {
        return object :
            NetworkBoundResource<TelevisionDetailsDataEntity, TelevisionDetailsResponse>(appExecutors) {
            override fun loadFromDatabase(): LiveData<TelevisionDetailsDataEntity> {
                // load genres
                val genre = ArrayList<GenresDataEntity>()
                val loadGenre = localDataSource.getTvShowGenres(id)
                for (position in loadGenre.indices) {
                    val item = GenresDataEntity(
                        id = loadGenre[position].id,
                        name = loadGenre[position].name
                    )
                    genre.add(item)
                }

                // load production companies
                val companies = ArrayList<ProductionCompaniesDataEntity>()
                val loadCompany = localDataSource.getTvShowProductionCompanies(id)
                for (position in loadCompany.indices) {
                    val item = ProductionCompaniesDataEntity(
                        id = loadCompany[position].id,
                        name = loadCompany[position].name,
                        logoPath = loadCompany[position].logoPath
                    )
                    companies.add(item)
                }

                // load creators
                val creators = ArrayList<CreatedByDataEntity>()
                val loadCreator = localDataSource.getTvShowCreators(id)
                for (position in loadCreator.indices) {
                    val item = CreatedByDataEntity(
                        gender = loadCreator[position].gender,
                        creditId = loadCreator[position].creditId,
                        name = loadCreator[position].name,
                        profilePath = loadCreator[position].profilePath,
                        id = loadCreator[position].id
                    )
                    creators.add(item)
                }

                // load details
                val details = TelevisionDetailsDataEntity()
                val loadDetails = localDataSource.getTvShowDetails(id)
                if (loadDetails != null) {
                    details.apply {
                        genres = genre
                        name = loadDetails.name
                        tagline = loadDetails.tagline
                        status = loadDetails.status
                        overview = loadDetails.overview
                        voteAverage = loadDetails.voteAverage
                        posterPath = loadDetails.posterPath
                        backdropPath = loadDetails.backdropPath
                        originalName = loadDetails.originalName
                        createdBy = creators
                        episodeRunTime = listOf(loadDetails.episodeRunTime)
                        firstAirDate = loadDetails.firstAirDate
                        numberOfEpisodes = loadDetails.numberOfEpisodes
                        numberOfSeasons = loadDetails.numberOfSeasons
                        productionCompanies = companies
                    }
                }

                return MutableLiveData(details)
            }

            override fun shouldFetch(data: TelevisionDetailsDataEntity?): Boolean {
                return if (NetUtil.isOnline(context)) {
                    true
                } else {
                    data == null
                }
            }

            override fun createCall(): LiveData<ApiResponse<TelevisionDetailsResponse>> {
                return remoteDataSource.getTvShowDetails(id)
            }

            override fun saveCallResult(data: TelevisionDetailsResponse) {
                // save genre
                val genre = data.genres
                if (genre != null) {
                    for (position in genre.indices) {
                        val item = TelevisionGenreEntity(
                            id = genre[position]?.id,
                            name = genre[position]?.name
                        )
                        localDataSource.insertTvShowGenres(item)

                        val crossRef = TelevisionGenreCrossRef(
                            tvId = id,
                            genreId = genre[position]?.id!!
                        )
                        localDataSource.insertTvShowCrossGenres(crossRef)
                    }
                }

                // save companies
                val company = data.productionCompanies
                if (company != null) {
                    for (position in company.indices) {
                        val item = TelevisionProductionCompanyEntity(
                            id = company[position]?.id,
                            name = company[position]?.name,
                            logoPath = company[position]?.logoPath
                        )
                        localDataSource.insertTvShowProductionCompany(item)

                        val crossRef = TelevisionProductionCompanyCrossRef(
                            tvId = id,
                            productionId = company[position]?.id!!
                        )
                        localDataSource.insertTvShowCrossProdCompany(crossRef)
                    }
                }

                // save creators
                val creator = data.createdBy
                if (creator != null) {
                    for (position in creator.indices) {
                        val item = TelevisionCreatedByEntity(
                            id = creator[position]?.id,
                            name = creator[position]?.name,
                            creditId = creator[position]?.creditId,
                            gender = creator[position]?.gender,
                            profilePath = creator[position]?.profilePath
                        )
                        localDataSource.insertTvShowCreators(item)
                        
                        val crossRef = TelevisionCreatorCrossRef(
                            tvId = id,
                            creatorId = creator[position]?.id!!
                        )
                        localDataSource.insertTvShowCrossCreator(crossRef)
                    }
                }
                
                // save details
                val details = TelevisionDetailsEntity(
                    tvId = id,
                    numberOfEpisodes = data.numberOfEpisodes,
                    backdropPath = data.backdropPath,
                    numberOfSeasons = data.numberOfSeasons,
                    firstAirDate = data.firstAirDate,
                    overview = data.overview,
                    posterPath = data.posterPath,
                    originalName = data.originalName,
                    voteAverage = data.voteAverage,
                    name = data.name,
                    tagline = data.tagline,
                    episodeRunTime = data.episodeRunTime?.get(0) ?: 0,
                    status = data.status
                )
                localDataSource.insertTvShowDetails(details)
            }

        }.asLiveData()
    }

}