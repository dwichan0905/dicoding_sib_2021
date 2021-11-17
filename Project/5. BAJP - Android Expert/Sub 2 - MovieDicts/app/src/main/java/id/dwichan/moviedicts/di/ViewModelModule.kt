package id.dwichan.moviedicts.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.dwichan.moviedicts.ui.detail.movies.DetailMoviesViewModel
import id.dwichan.moviedicts.ui.detail.television.DetailTelevisionShowViewModel
import id.dwichan.moviedicts.ui.main.movies.TrendingMoviesViewModel
import id.dwichan.moviedicts.ui.main.television.TrendingTelevisionShowViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrendingMoviesViewModel::class)
    abstract fun bindTrendingMoviesViewModel(
        trendingMoviesViewModel: TrendingMoviesViewModel
    ) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrendingTelevisionShowViewModel::class)
    abstract fun bindTrendingTvShowViewModel(
        trendingTelevisionShowViewModel: TrendingTelevisionShowViewModel
    ) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DetailMoviesViewModel::class)
    abstract fun bindDetailMoviesViewModel(
        detailMoviesViewModel: DetailMoviesViewModel
    ) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DetailTelevisionShowViewModel::class)
    abstract fun bindDetailTvShowViewModel(
        detailTelevisionShowViewModel: DetailTelevisionShowViewModel
    ) : ViewModel
}