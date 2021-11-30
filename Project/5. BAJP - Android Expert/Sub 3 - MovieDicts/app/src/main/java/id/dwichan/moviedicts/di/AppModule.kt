package id.dwichan.moviedicts.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.dwichan.moviedicts.core.domain.usecase.*

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMoviesUseCase(moviesInteractor: MoviesInteractor):
            MoviesUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideTelevisionShowUseCase(televisionShowInteractor: TelevisionShowInteractor):
            TelevisionShowUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideBookmarkUseCase(bookmarkInteractor: BookmarkInteractor):
            BookmarkUseCase
}