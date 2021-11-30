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

    val bookmarkStatus: LiveData<Boolean> = Transformations.switchMap(_tvId) { id ->
        televisionShowUseCase.getBookmarkStatus(id)
    }

    fun setAsBookmark(data: MovieTelevisionDataEntity) {
        televisionShowUseCase.setTvShowAsBookmark(data)
    }

    fun removeFromBookmark(data: MovieTelevisionDataEntity) {
        televisionShowUseCase.removeFromBookmark(data)
    }
}