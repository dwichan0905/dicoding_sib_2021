package id.dwichan.moviedicts.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.dwichan.moviedicts.core.data.repository.local.dao.FavoriteDao
import id.dwichan.moviedicts.core.data.repository.local.dao.MoviesDao
import id.dwichan.moviedicts.core.data.repository.local.dao.TelevisionShowsDao
import id.dwichan.moviedicts.core.data.repository.local.dao.TrendingDao
import id.dwichan.moviedicts.core.data.repository.local.db.MovieDictsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideFavoriteDao(database: MovieDictsDatabase): FavoriteDao =
        database.favoriteDao()

    @Provides
    fun provideMoviesDao(database: MovieDictsDatabase): MoviesDao =
        database.moviesDao()

    @Provides
    fun provideTelevisionShowsDao(database: MovieDictsDatabase): TelevisionShowsDao =
        database.televisionShowsDao()

    @Provides
    fun provideTrendingDao(database: MovieDictsDatabase): TrendingDao =
        database.trendingDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDictsDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MovieDictsDatabase::class.java,
            "MovieDictsDatabase.db"
        )
            .allowMainThreadQueries()
            .build()
}