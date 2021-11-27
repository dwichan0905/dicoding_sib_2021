package id.dwichan.moviedicts.core.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import id.dwichan.moviedicts.core.data.entity.*
import id.dwichan.moviedicts.core.data.repository.local.LocalDataSource
import id.dwichan.moviedicts.core.data.repository.local.entity.FavoriteEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.remote.ApiResponse
import id.dwichan.moviedicts.core.data.repository.remote.RemoteDataSource
import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieDetailsResponse
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResponse
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.core.util.AppExecutors
import id.dwichan.moviedicts.core.util.NetUtil
import id.dwichan.moviedicts.vo.Resource
import id.dwichan.moviedicts.vo.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    @ApplicationContext private val context: Context
) : MoviesDataSource {

    override fun getFavoriteStatus(id: Int): Boolean {
        val db = localDataSource.getFavoriteMovie(id)
        return db.isNotEmpty()
    }

    override fun setMovieAsFavorite(data: MovieTelevisionDataEntity) {
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

    override fun removeFavoriteMovie(data: MovieTelevisionDataEntity) {
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

    override fun getTrendingMoviesToday(): LiveData<Resource<List<TrendingResultsDataEntity>>> {
        return object :
            NetworkBoundResource<List<TrendingResultsDataEntity>, TrendingResponse>(appExecutors) {
            override fun loadFromDatabase(): LiveData<List<TrendingResultsDataEntity>> {
                val liveData = MutableLiveData<List<TrendingResultsDataEntity>>()
                val data = ArrayList<TrendingResultsDataEntity>()
                val db = localDataSource.getTrendingMoviesToday()

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
                return remoteDataSource.getTrendingMoviesToday()
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
                            mediaType = Type.MEDIA_TYPE_MOVIES,
                            interval = Type.INTERVAL_TODAY
                        )
                        localDataSource.insertTrendingEntity(entity)
                    }
                }
            }

        }.asLiveData()
    }

    override fun getTrendingMoviesWeekly(): LiveData<Resource<List<TrendingResultsDataEntity>>> {
        return object :
            NetworkBoundResource<List<TrendingResultsDataEntity>, TrendingResponse>(appExecutors) {
            override fun loadFromDatabase(): LiveData<List<TrendingResultsDataEntity>> {
                val liveData = MutableLiveData<List<TrendingResultsDataEntity>>()
                val data = ArrayList<TrendingResultsDataEntity>()
                val db = localDataSource.getTrendingMoviesWeekly()

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
                return remoteDataSource.getTrendingMoviesWeekly()
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
                            mediaType = Type.MEDIA_TYPE_MOVIES,
                            interval = Type.INTERVAL_WEEKLY
                        )
                        localDataSource.insertTrendingEntity(entity)
                    }
                }
            }

        }.asLiveData()
    }

    @Suppress("SENSELESS_COMPARISON") // avoid warning comparison always true
    override fun getMovieDetails(id: Int): LiveData<Resource<MovieDetailsDataEntity>> {
        return object :
            NetworkBoundResource<MovieDetailsDataEntity, MovieDetailsResponse>(appExecutors) {
            override fun loadFromDatabase(): LiveData<MovieDetailsDataEntity> {
                // load genres
                val genre = ArrayList<GenresDataEntity>()
                val loadGenre = localDataSource.getMovieGenres(id)
                for (position in loadGenre.indices) {
                    val itemGenre = GenresDataEntity(
                        id = loadGenre[position].id,
                        name = loadGenre[position].name
                    )
                    genre.add(itemGenre)
                }

                // load companies
                val companies = ArrayList<ProductionCompaniesDataEntity>()
                val loadCompany = localDataSource.getMovieProductionCompanies(id)
                for (position in loadCompany.indices) {
                    val itemCompany = ProductionCompaniesDataEntity(
                        id = loadCompany[position].id,
                        name = loadCompany[position].name,
                        logoPath = loadCompany[position].logoPath
                    )
                    companies.add(itemCompany)
                }

                // load details
                val details = MovieDetailsDataEntity()
                val loadDetails = localDataSource.getMovieDetails(id)
                if (loadDetails != null) {
                    details.apply {
                        runtime = loadDetails.runtime
                        posterPath = loadDetails.posterPath
                        backdropPath = loadDetails.backdropPath
                        genres = genre
                        productionCompanies = companies
                        adult = loadDetails.adult
                        originalTitle = loadDetails.originalTitle
                        overview = loadDetails.overview
                        releaseDate = loadDetails.releaseDate
                        status = loadDetails.status
                        tagline = loadDetails.tagline
                        title = loadDetails.title
                        voteAverage = loadDetails.voteAverage
                    }
                }
                return MutableLiveData(details)
            }

            override fun shouldFetch(data: MovieDetailsDataEntity?): Boolean {
                return if (NetUtil.isOnline(context)) {
                    true
                } else {
                    data == null
                }
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailsResponse>> {
                return remoteDataSource.getMovieDetails(id)
            }

            override fun saveCallResult(data: MovieDetailsResponse) {
                // save genre
                val genre = data.genres
                if (genre != null) {
                    for (position in genre.indices) {
                        val item = MovieGenreEntity(
                            id = genre[position]?.id,
                            name = genre[position]?.name
                        )
                        localDataSource.insertMovieGenre(item)

                        val crossRef = MovieGenreCrossRef(
                            movieId = id,
                            genreId = genre[position]?.id!!
                        )
                        localDataSource.insertMovieCrossGenre(crossRef)
                    }
                }

                // save companies
                val company = data.productionCompanies
                if (company != null) {
                    for (position in company.indices) {
                        val item = MovieProductionCompanyEntity(
                            id = company[position]?.id,
                            name = company[position]?.name,
                            logoPath = company[position]?.logoPath
                        )
                        localDataSource.insertMovieProductionCompany(item)

                        val crossRef = MovieProductionCompanyCrossRef(
                            movieId = id,
                            productionId = company[position]?.id!!
                        )
                        localDataSource.insertMovieCrossProductionCompany(crossRef)
                    }
                }

                // then, save details
                val details = MovieDetailsEntity(
                    id = id,
                    title = data.title,
                    originalTitle = data.originalTitle,
                    adult = data.adult,
                    backdropPath = data.backdropPath,
                    posterPath = data.posterPath,
                    voteAverage = data.voteAverage,
                    overview = data.overview,
                    releaseDate = data.releaseDate,
                    runtime = data.runtime,
                    status = data.status,
                    tagline = data.tagline
                )
                localDataSource.insertMovieDetails(details)
            }
        }.asLiveData()
    }

}