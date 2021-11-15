package id.dwichan.moviedicts.core.di

import dagger.Binds
import dagger.Module
import id.dwichan.moviedicts.core.data.repository.MoviesRepository
import id.dwichan.moviedicts.core.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource

@Module(includes = [NetworkModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideMoviesRepository(moviesRepository: MoviesRepository): MoviesDataSource

    @Binds
    abstract fun provideTelevisionShowRepository(televisionShowRepository: TelevisionShowRepository):
            TelevisionShowDataSource

}