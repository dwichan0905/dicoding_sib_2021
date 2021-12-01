package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TelevisionDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

class TelevisionShowInteractor @Inject constructor(
    private val televisionShowDataSource: TelevisionShowDataSource
) : TelevisionShowUseCase {

    override fun getBookmarkStatus(id: Int): LiveData<Boolean> =
        televisionShowDataSource.getBookmarkStatus(id)

    override fun setTvShowAsBookmark(data: MovieTelevisionDataEntity) {
        televisionShowDataSource.setTvShowAsBookmark(data)
    }

    override fun removeFromBookmark(data: MovieTelevisionDataEntity) {
        televisionShowDataSource.removeFromBookmark(data)
    }

    override fun getTrendingTelevisionShowToday(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>> =
        televisionShowDataSource.getTrendingTelevisionShowToday()

    override fun getTrendingTelevisionShowWeekly(): LiveData<Resource<PagedList<TrendingResultsDataEntity>>> =
        televisionShowDataSource.getTrendingTelevisionShowWeekly()

    override fun getTelevisionShowDetails(id: Int): LiveData<Resource<TelevisionDetailsDataEntity>> =
        televisionShowDataSource.getTelevisionShowDetails(id)

}