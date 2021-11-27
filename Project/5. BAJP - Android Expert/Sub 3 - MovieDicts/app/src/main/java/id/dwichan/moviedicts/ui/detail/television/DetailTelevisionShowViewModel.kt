package id.dwichan.moviedicts.ui.detail.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.data.entity.TelevisionDetailsDataEntity
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase
import id.dwichan.moviedicts.vo.Resource
import javax.inject.Inject

@HiltViewModel
class DetailTelevisionShowViewModel @Inject constructor(
    private val televisionShowUseCase: TelevisionShowUseCase
) : ViewModel() {

    private var _tvId = MutableLiveData<Int>()

    fun setTelevisionId(id: Int) {
        _tvId.value = id
    }

    val tvShowDetails: LiveData<Resource<TelevisionDetailsDataEntity>> =
        Transformations.switchMap(_tvId) { id ->
            televisionShowUseCase.getTelevisionShowDetails(id)
        }

    val favoriteStatus: Boolean = televisionShowUseCase.getFavoriteStatus(_tvId.value ?: 0)

    fun setAsFavorite(data: MovieTelevisionDataEntity) {
        televisionShowUseCase.setTvShowAsFavorite(data)
    }

    fun removeFromFavorite(data: MovieTelevisionDataEntity) {
        televisionShowUseCase.removeFavoriteTvShow(data)
    }
}