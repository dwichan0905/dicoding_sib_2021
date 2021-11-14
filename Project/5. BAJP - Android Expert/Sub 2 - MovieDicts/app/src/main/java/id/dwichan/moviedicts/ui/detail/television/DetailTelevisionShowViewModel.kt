package id.dwichan.moviedicts.ui.detail.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.TelevisionShowRepository
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.util.SingleEvent

class DetailTelevisionShowViewModel(private val televisionShowRepository: TelevisionShowRepository) :
    ViewModel() {

    val data: LiveData<TelevisionDetailsResponse> = televisionShowRepository.televisionDetails

    val isLoading: LiveData<Boolean> = televisionShowRepository.isLoadingDetails

    val errorReason: LiveData<SingleEvent<String>> = televisionShowRepository.errorReason

    private var _tvId = MutableLiveData<Int>()

    fun setTelevisionId(id: Int) {
        _tvId.value = id
    }

    fun fetchTelevisionShowDetails() {
        televisionShowRepository.getTelevisionShowDetails(_tvId.value ?: 0)
    }
}