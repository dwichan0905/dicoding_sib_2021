package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.vo.Resource

interface MoviesUseCase {

    fun getBookmarkStatus(id: Int): LiveData<Boolean>

    fun setMovieAsBookmark(data: MovieTelevisionDataEntity)

    fun removeFromBookmark(data: MovieTelevisionDataEntity)

    fun getTrendingMoviesToday(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>>

    fun getTrendingMoviesWeekly(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>>

    fun getMovieDetails(id: Int): LiveData<Resource<MovieDetailsDataEntity>>

}