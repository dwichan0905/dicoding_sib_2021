package id.dwichan.moviedicts.di

import dagger.Component
import id.dwichan.moviedicts.core.di.CoreComponent
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesActivity
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowActivity
import id.dwichan.moviedicts.ui.main.movies.MoviesFragment
import id.dwichan.moviedicts.ui.main.television.TelevisionShowFragment

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(moviesFragment: MoviesFragment)
    fun inject(televisionShowFragment: TelevisionShowFragment)
    fun inject(detailMoviesActivity: DetailMoviesActivity)
    fun inject(detailTelevisionShowActivity: DetailTelevisionShowActivity)
}