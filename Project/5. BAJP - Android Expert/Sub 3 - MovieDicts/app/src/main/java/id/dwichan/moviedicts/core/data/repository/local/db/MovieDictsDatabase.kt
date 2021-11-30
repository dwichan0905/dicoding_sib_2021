package id.dwichan.moviedicts.core.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.dwichan.moviedicts.core.data.repository.local.dao.BookmarkDao
import id.dwichan.moviedicts.core.data.repository.local.dao.MoviesDao
import id.dwichan.moviedicts.core.data.repository.local.dao.TelevisionShowsDao
import id.dwichan.moviedicts.core.data.repository.local.dao.TrendingDao
import id.dwichan.moviedicts.core.data.repository.local.entity.BookmarkEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.MovieProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers.MovieProductionCompanyCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionCreatedByEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionDetailsEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionGenreEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.TelevisionProductionCompanyEntity
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionCreatorCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionGenreCrossRef
import id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers.TelevisionProductionCompanyCrossRef

@Database(
    entities = [
        TrendingEntity::class,
        BookmarkEntity::class,

        MovieDetailsEntity::class,
        MovieGenreEntity::class,
        MovieProductionCompanyEntity::class,
        MovieGenreCrossRef::class,
        MovieProductionCompanyCrossRef::class,

        TelevisionDetailsEntity::class,
        TelevisionGenreEntity::class,
        TelevisionProductionCompanyEntity::class,
        TelevisionCreatedByEntity::class,
        TelevisionCreatorCrossRef::class,
        TelevisionGenreCrossRef::class,
        TelevisionProductionCompanyCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDictsDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

    abstract fun moviesDao(): MoviesDao

    abstract fun televisionShowsDao(): TelevisionShowsDao

    abstract fun trendingDao(): TrendingDao
}