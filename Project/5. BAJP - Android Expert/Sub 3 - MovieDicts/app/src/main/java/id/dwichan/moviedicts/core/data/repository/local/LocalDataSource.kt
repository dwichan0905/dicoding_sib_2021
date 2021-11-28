package id.dwichan.moviedicts.core.data.repository.local

import id.dwichan.moviedicts.core.data.repository.local.dao.FavoriteDao
import id.dwichan.moviedicts.core.data.repository.local.dao.MoviesDao
import id.dwichan.moviedicts.core.data.repository.local.dao.TelevisionShowsDao
import id.dwichan.moviedicts.core.data.repository.local.dao.TrendingDao
import id.dwichan.moviedicts.core.data.repository.local.entity.FavoriteEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.output.MovieWithGenreOut
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.output.MovieWithProdCompanyOut
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionCreatedByEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionCreatorCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output.TelevisionWithCreatedByOut
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output.TelevisionWithGenreOut
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output.TelevisionWithProductionCompanyOut
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val moviesDao: MoviesDao,
    private val televisionShowsDao: TelevisionShowsDao,
    private val trendingDao: TrendingDao
) {

    fun getTrendingMoviesToday(): List<TrendingEntity> =
        trendingDao.getTrendingMoviesToday()

    fun getTrendingMoviesWeekly(): List<TrendingEntity> =
        trendingDao.getTrendingMoviesWeekly()

    fun getTrendingTelevisionShowToday(): List<TrendingEntity> =
        trendingDao.getTrendingTelevisionShowToday()

    fun getTrendingTelevisionShowWeekly(): List<TrendingEntity> =
        trendingDao.getTrendingTelevisionShowWeekly()

    fun insertTrendingEntity(trendingEntity: TrendingEntity) {
        trendingDao.insertWithTimestamp(trendingEntity)
    }

    fun getFavoriteMovie(id: Int): List<FavoriteEntity> = favoriteDao.getFavoriteMovie(id)

    fun getAllFavoriteMovie(): List<FavoriteEntity> = favoriteDao.getAllFavoriteMovie()

    fun insertFavoriteMovie(favorite: FavoriteEntity) {
        favoriteDao.insertFavoriteMovie(favorite)
    }

    fun deleteFavorite(favorite: FavoriteEntity) {
        favoriteDao.deleteFavorite(favorite)
    }

    fun getMovieDetails(id: Int): MovieDetailsEntity = moviesDao.getMovieDetails(id)

    fun getMovieGenres(movieId: Int): List<MovieWithGenreOut> = moviesDao.getMovieGenres(movieId)

    fun getMovieProductionCompanies(movieId: Int): List<MovieWithProdCompanyOut> =
        moviesDao.getMovieProductionCompanies(movieId)

    fun insertMovieDetails(movieDetails: MovieDetailsEntity) {
        moviesDao.insertMovieDetails(movieDetails)
    }

    fun insertMovieGenre(movieGenre: MovieGenreEntity) {
        moviesDao.insertMovieGenre(movieGenre)
    }

    fun insertMovieCrossGenre(movieGenreCrossRef: MovieGenreCrossRef) {
        moviesDao.insertMovieCrossGenre(movieGenreCrossRef)
    }

    fun insertMovieProductionCompany(productionCompany: MovieProductionCompanyEntity) {
        moviesDao.insertMovieProductionCompany(productionCompany)
    }

    fun insertMovieCrossProductionCompany(movieProductionCompanyCrossRef: MovieProductionCompanyCrossRef) {
        moviesDao.insertMovieCrossProductionCompany(movieProductionCompanyCrossRef)
    }

    fun getTvShowDetails(id: Int): TelevisionDetailsEntity =
        televisionShowsDao.getTvShowDetails(id)

    fun getTvShowGenres(id: Int): List<TelevisionWithGenreOut> =
        televisionShowsDao.getTvShowGenres(id)

    fun getTvShowProductionCompanies(id: Int): List<TelevisionWithProductionCompanyOut> =
        televisionShowsDao.getTvShowProductionCompanies(id)

    fun getTvShowCreators(id: Int): List<TelevisionWithCreatedByOut> =
        televisionShowsDao.getTvShowCreators(id)

    fun insertTvShowDetails(televisionDetails: TelevisionDetailsEntity) {
        televisionShowsDao.insertTvShowDetails(televisionDetails)
    }

    fun insertTvShowProductionCompany(tvProductionCompany: TelevisionProductionCompanyEntity) {
        televisionShowsDao.insertTvShowProductionCompany(tvProductionCompany)
    }

    fun insertTvShowCrossProdCompany(tvProductionCompanyCrossRef: TelevisionProductionCompanyCrossRef) {
        televisionShowsDao.insertTvShowCrossProdCompany(tvProductionCompanyCrossRef)
    }

    fun insertTvShowGenres(tvGenre: TelevisionGenreEntity) {
        televisionShowsDao.insertTvShowGenre(tvGenre)
    }

    fun insertTvShowCrossGenres(tvGenreCrossRef: TelevisionGenreCrossRef) {
        televisionShowsDao.insertTvShowCrossGenre(tvGenreCrossRef)
    }

    fun insertTvShowCreators(televisionCreator: TelevisionCreatedByEntity) {
        televisionShowsDao.insertTvShowCreator(televisionCreator)
    }

    fun insertTvShowCrossCreator(tvCreatorCrossRef: TelevisionCreatorCrossRef) {
        televisionShowsDao.insertTvShowCrossCreator(tvCreatorCrossRef)
    }
}