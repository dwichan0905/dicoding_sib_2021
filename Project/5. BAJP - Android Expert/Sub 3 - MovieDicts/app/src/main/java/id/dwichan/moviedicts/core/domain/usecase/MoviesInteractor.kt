package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesDataSource: MoviesDataSource) :
    MoviesUseCase {

    override fun getBookmarkStatus(id: Int): LiveData<Boolean> =
        moviesDataSource.getBookmarkStatus(id)

    override fun setMovieAsBookmark(data: MovieTelevisionDataEntity) {
        moviesDataSource.setMovieAsBookmark(data)
    }

    override fun removeFromBookmark(data: MovieTelevisionDataEntity) {
        moviesDataSource.removeFromBookmark(data)
    }

    override fun getTrendingMoviesToday(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>> =
        moviesDataSource.getTrendingMoviesToday()

    override fun getTrendingMoviesWeekly(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>> =
        moviesDataSource.getTrendingMoviesWeekly()

    override fun getMovieDetails(id: Int): LiveData<Resource<MovieDetailsDataEntity>> =
        moviesDataSource.getMovieDetails(id)

}