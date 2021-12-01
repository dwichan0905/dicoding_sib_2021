package id.dwichan.moviedicts.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TelevisionDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.vo.Resource

interface TelevisionShowDataSource {

    fun getBookmarkStatus(id: Int): LiveData<Boolean>

    fun setTvShowAsBookmark(data: MovieTelevisionDataEntity)

    fun removeFromBookmark(data: MovieTelevisionDataEntity)

    fun getTrendingTelevisionShowToday(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>>

    fun getTrendingTelevisionShowWeekly(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>>

    fun getTelevisionShowDetails(id: Int): LiveData<Resource<TelevisionDetailsDataEntity>>
}