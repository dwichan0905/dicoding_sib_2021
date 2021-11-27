package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TelevisionDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.domain.repository.TelevisionShowDataSource
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

class TelevisionShowInteractor @Inject constructor(
    private val televisionShowDataSource: TelevisionShowDataSource
) : TelevisionShowUseCase {

    override fun getFavoriteStatus(id: Int): Boolean =
        televisionShowDataSource.getFavoriteStatus(id)

    override fun setTvShowAsFavorite(data: MovieTelevisionDataEntity) {
        televisionShowDataSource.setTvShowAsFavorite(data)
    }

    override fun removeFavoriteTvShow(data: MovieTelevisionDataEntity) {
        televisionShowDataSource.removeFavoriteTvShow(data)
    }

    override fun getTrendingTelevisionShowToday(): LiveData<Resource<List<TrendingResultsDataEntity>>> =
        televisionShowDataSource.getTrendingTelevisionShowToday()

    override fun getTrendingTelevisionShowWeekly(): LiveData<Resource<List<TrendingResultsDataEntity>>> =
        televisionShowDataSource.getTrendingTelevisionShowWeekly()

    override fun getTelevisionShowDetails(id: Int): LiveData<Resource<TelevisionDetailsDataEntity>> =
        televisionShowDataSource.getTelevisionShowDetails(id)

}