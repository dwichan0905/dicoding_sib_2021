package id.dwichan.moviedicts.ui.detail.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.dwichan.moviedicts.data.repository.remote.api.ApiService
import id.dwichan.moviedicts.data.repository.remote.response.television.TelevisionDetailsResponse
import id.dwichan.moviedicts.util.SingleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTelevisionShowViewModel: ViewModel() {

    private var _data = MutableLiveData<TelevisionDetailsResponse>()
    val data: LiveData<TelevisionDetailsResponse> = _data

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorReason = MutableLiveData<SingleEvent<String>>()
    val errorReason: LiveData<SingleEvent<String>> = _errorReason

    private var _tvId = MutableLiveData<Int>()

    init {
        _errorReason.value = SingleEvent("")
    }

    fun setTelevisionId(id: Int) {
        _tvId.value = id
    }

    fun fetchTelevisionShowDetails() {
        _isLoading.value = true
        _errorReason.value = SingleEvent("")

        val api = ApiService.getApiService().getTelevisionShowDetails(_tvId.value ?: 0)
        api.enqueue(object : Callback<TelevisionDetailsResponse> {
            override fun onResponse(
                call: Call<TelevisionDetailsResponse>,
                response: Response<TelevisionDetailsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _data.postValue(responseBody)
                    } else {
                        _errorReason.value = SingleEvent(
                            "Server didn't returned the correct respond [${response.code()}]"
                        )
                        _data.postValue(TelevisionDetailsResponse())
                    }
                } else {
                    _errorReason.value = SingleEvent(
                        "Server didn't returned the correct respond [${response.code()}]"
                    )
                    _data.postValue(TelevisionDetailsResponse())
                }
            }

            override fun onFailure(call: Call<TelevisionDetailsResponse>, t: Throwable) {
                _isLoading.value = false
                _errorReason.value = SingleEvent(t.message as String)
                _data.postValue(TelevisionDetailsResponse())
                t.printStackTrace()
            }
        })
    }
}