package id.dwichan.moviedicts.core.domain.repository

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.vo.Resource

interface MoviesDataSource {

    fun getBookmarkStatus(id: Int): LiveData<Boolean>

    fun setMovieAsBookmark(data: MovieTelevisionDataEntity)

    fun removeFromBookmark(data: MovieTelevisionDataEntity)

    fun getTrendingMoviesToday(): LiveData<Resource<List<TrendingResultsDataEntity>>>

    fun getTrendingMoviesWeekly(): LiveData<Resource<List<TrendingResultsDataEntity>>>

    fun getMovieDetails(id: Int): LiveData<Resource<MovieDetailsDataEntity>>
}