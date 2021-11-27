package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.repository.MoviesDataSource
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesDataSource: MoviesDataSource) :
    MoviesUseCase {

    override fun getFavoriteStatus(id: Int): Boolean =
        moviesDataSource.getFavoriteStatus(id)

    override fun setMovieAsFavorite(data: MovieTelevisionDataEntity) {
        moviesDataSource.setMovieAsFavorite(data)
    }

    override fun removeFavoriteMovie(data: MovieTelevisionDataEntity) {
        moviesDataSource.removeFavoriteMovie(data)
    }

    override fun getTrendingMoviesToday(): LiveData<Resource<List<TrendingResultsDataEntity>>> =
        moviesDataSource.getTrendingMoviesToday()

    override fun getTrendingMoviesWeekly(): LiveData<Resource<List<TrendingResultsDataEntity>>> =
        moviesDataSource.getTrendingMoviesWeekly()

    override fun getMovieDetails(id: Int): LiveData<Resource<MovieDetailsDataEntity>> =
        moviesDataSource.getMovieDetails(id)

}