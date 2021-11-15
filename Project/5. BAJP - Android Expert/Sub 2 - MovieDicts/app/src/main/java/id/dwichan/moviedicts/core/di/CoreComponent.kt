package id.dwichan.moviedicts.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class, NetworkModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideMoviesRepository(): MoviesDataSource

    fun provideTelevisionShowRepository(): TelevisionShowDataSource
}