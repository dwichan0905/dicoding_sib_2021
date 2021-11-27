package id.dwichan.moviedicts.core.domain.usecase

import androidx.lifecycle.LiveData
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TelevisionDetailsDataEntity
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.vo.Resource

interface TelevisionShowUseCase {

    fun getFavoriteStatus(id: Int): Boolean

    fun setTvShowAsFavorite(data: MovieTelevisionDataEntity)

    fun removeFavoriteTvShow(data: MovieTelevisionDataEntity)

    fun getTrendingTelevisionShowToday(): LiveData<Resource<List<TrendingResultsDataEntity>>>

    fun getTrendingTelevisionShowWeekly(): LiveData<Resource<List<TrendingResultsDataEntity>>>

    fun getTelevisionShowDetails(id: Int): LiveData<Resource<TelevisionDetailsDataEntity>>
}