package id.dwichan.moviedicts.di

import dagger.Binds
import dagger.Module
import id.dwichan.moviedicts.core.domain.usecase.MoviesInteractor
import id.dwichan.moviedicts.core.domain.usecase.MoviesUseCase
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowInteractor
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase

@Module
abstract class AppModule {

    @Binds
    abstract fun provideMoviesUseCase(moviesInteractor: MoviesInteractor):
            MoviesUseCase

    @Binds
    abstract fun provideTelevisionShowUseCase(televisionShowInteractor: TelevisionShowInteractor):
            TelevisionShowUseCase
}