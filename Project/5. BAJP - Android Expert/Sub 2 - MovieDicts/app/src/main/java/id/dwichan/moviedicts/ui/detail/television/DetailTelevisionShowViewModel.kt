package id.dwichan.moviedicts.ui.detail.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.core.domain.usecase.TelevisionShowUseCase
import id.dwichan.moviedicts.core.util.SingleEvent

class DetailTelevisionShowViewModel(private val televisionShowUseCase: TelevisionShowUseCase) :
    ViewModel() {

    val data: LiveData<TelevisionDetailsResponse> =
        televisionShowUseCase.getTelevisionShowDetailsData()

    val isLoading: LiveData<Boolean> = televisionShowUseCase.getLoadingDetailsState()

    val errorReason: LiveData<SingleEvent<String>> = televisionShowUseCase.getErrorReason()

    private var _tvId = MutableLiveData<Int>()

    fun setTelevisionId(id: Int) {
        _tvId.value = id
    }

    fun fetchTelevisionShowDetails() {
        televisionShowUseCase.getTelevisionShowDetails(_tvId.value ?: 0)
    }
}